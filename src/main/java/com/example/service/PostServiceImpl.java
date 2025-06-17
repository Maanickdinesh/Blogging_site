package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PostRequestDTO;
import com.example.dto.PostResponseDTO;
import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.mapper.PostMapper;
import com.example.repo.CategoryRepository;
import com.example.repo.PostRepository;
import com.example.repo.UserRepository;
import com.example.utils.CommonConstant.PostStatus;

@Service
public class PostServiceImpl implements PostService  {

	@Autowired
	private  PostRepository postRepository;
	@Autowired
    private  CategoryRepository categoryRepository;
	@Autowired
	private UserRepository  userRepository;

    

    @Override
    public PostResponseDTO createPost(PostRequestDTO request, String createdBy) {
    	 User user = userRepository.findByEmail(createdBy)
                 .orElseThrow(() -> new RuntimeException("User not found with email: " + createdBy));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Post post = PostMapper.toEntity(request, category, createdBy);
        Post savedPost = postRepository.save(post);
        return PostMapper.toDto(savedPost);
    }

    @Override
    public List<PostResponseDTO> getAllPublishedPosts() {
        return postRepository.findByStatus(PostStatus.PUBLISHED)
                .stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> getPendingPosts() {
        return postRepository.findByStatus(PostStatus.PENDING)
                .stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> getUserPosts(String username) {
        return postRepository.findByCreatedBy(username)
                .stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO approvePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus(PostStatus.PUBLISHED);
        post.setUpdatedDate(java.time.LocalDate.now().toString());
        return PostMapper.toDto(postRepository.save(post));
    }
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }
//    @Override
//    public List<PostResponseDTO> getPostsByRoleAndStatus(String role, String status) {
//        List<Post> posts = postRepository.findByRoleAndStatusDynamic(
//                (role != null && !role.isBlank()) ? role.toUpperCase() : null,
//                (status != null && !status.isBlank()) ? status.toUpperCase() : null
//        );
//        List<PostResponseDTO> postResponseDTO = posts.stream()
//                .map(PostMapper::toDto)
//                .collect(Collectors.toList());
//        return postResponseDTO;
//    }

	@Override
	public List<PostResponseDTO> getAllUserPosts() {
		// TODO Auto-generated method stub
		List<Post> posts = postRepository.findAll();
        return posts.stream()
                .filter(post -> {
                    User user = userRepository.findByEmail(post.getCreatedBy()).orElse(null);
                    return user != null && user.getRole().equalsIgnoreCase("USER");
                })
                .map(PostMapper::toDto)
                .collect(Collectors.toList());	}

	@Override
	public List<PostResponseDTO> getAllAdminPosts() {
		// TODO Auto-generated method stub
		 List<Post> posts = postRepository.findAll();
	        return posts.stream()
	                .filter(post -> {
	                    User user = userRepository.findByEmail(post.getCreatedBy()).orElse(null);
	                    return user != null && user.getRole().equalsIgnoreCase("ADMIN");
	                })
	                .map(PostMapper::toDto)
	                .collect(Collectors.toList());	}

}
