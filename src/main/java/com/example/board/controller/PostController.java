package com.example.board.controller;

import com.example.board.request.PostSearchRequest;
import com.example.board.request.PostUpdateRequest;
import com.example.board.service.PostService;
import com.example.board.request.PostSaveRequest;
import com.example.board.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PostController {
    private final PostService postService;

    // 등록
    @PostMapping("/post")
    public Long save(@RequestBody @Valid PostSaveRequest postSaveRequest) {
        log.info("params={}", postSaveRequest.toString());
        return postService.register(postSaveRequest);
    }

    // 전체 조회
    @GetMapping()
    public List<PostResponse> getList(@ModelAttribute PostSearchRequest postSearchRequest) {
        return postService.getList(postSearchRequest);
    }

    // 1개 조회
    @GetMapping("/{id}")
    public Optional<PostResponse> findById(@PathVariable Long id) {
        return postService.findOne(id);
    }

    // 수정
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        return postService.update(id, postUpdateRequest);
    }
}
