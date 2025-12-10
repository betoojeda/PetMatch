package com.petmatch.service;

import com.petmatch.dto.CommentDto;
import com.petmatch.dto.LostPetPostDto;
import com.petmatch.mapper.CommentMapper;
import com.petmatch.mapper.LostPetPostMapper;
import com.petmatch.model.Comment;
import com.petmatch.model.LostPetPost;
import com.petmatch.model.User;
import com.petmatch.repository.CommentRepository;
import com.petmatch.repository.LostPetPostRepository;
import com.petmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LostPetPostService {

    private final LostPetPostRepository lostPetPostRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService; // Inyectar servicio de Cloudinary
    private final LostPetPostMapper lostPetPostMapper;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public Page<LostPetPostDto> getAllActivePosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<LostPetPost> posts = lostPetPostRepository.findByStatusNot(LostPetPost.PostStatus.REUNITED, pageable);
        return posts.map(lostPetPostMapper::toDto);
    }

    @Transactional
    public LostPetPostDto createPost(LostPetPostDto postDto, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        LostPetPost post = lostPetPostMapper.toEntity(postDto);
        post.setUser(user);
        post.setStatus(LostPetPost.PostStatus.LOST); // Por defecto
        
        LostPetPost savedPost = lostPetPostRepository.save(post);
        return lostPetPostMapper.toDto(savedPost);
    }

    @Transactional
    public LostPetPostDto addPhotoToPost(Long postId, MultipartFile file) throws IOException {
        LostPetPost post = lostPetPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        String photoUrl = cloudinaryService.uploadFile(file);
        post.setPhotoUrl(photoUrl);

        LostPetPost updatedPost = lostPetPostRepository.save(post);
        return lostPetPostMapper.toDto(updatedPost);
    }

    @Transactional
    public CommentDto addComment(Long postId, CommentDto commentDto, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        LostPetPost post = lostPetPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setUser(user);
        comment.setLostPetPost(post);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }
}
