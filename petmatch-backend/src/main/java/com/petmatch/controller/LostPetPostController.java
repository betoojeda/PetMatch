package com.petmatch.controller;

import com.petmatch.dto.CommentDto;
import com.petmatch.dto.LostPetPostDto;
import com.petmatch.service.LostPetPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/lost-pets")
@RequiredArgsConstructor
public class LostPetPostController {

    private final LostPetPostService lostPetPostService;

    @GetMapping
    public ResponseEntity<Page<LostPetPostDto>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<LostPetPostDto> posts = lostPetPostService.getAllActivePosts(page, size);
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<LostPetPostDto> createPost(
            @Valid @RequestBody LostPetPostDto postDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        LostPetPostDto createdPost = lostPetPostService.createPost(postDto, userDetails.getUsername());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/photo")
    public ResponseEntity<LostPetPostDto> uploadPhoto(
            @PathVariable Long postId,
            @RequestParam("file") MultipartFile file) throws IOException {
        LostPetPostDto updatedPost = lostPetPostService.addPhotoToPost(postId, file);
        return ResponseEntity.ok(updatedPost);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> addComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        CommentDto newComment = lostPetPostService.addComment(postId, commentDto, userDetails.getUsername());
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}
