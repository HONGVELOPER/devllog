package devlog.hong.service;

import devlog.hong.domain.entity.PostEntity;
import devlog.hong.domain.repository.PostRepository;
import devlog.hong.dto.PostRequestDto;
import devlog.hong.dto.PostResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Value("${verify.password}")
    private String envPassword;

    @Test
    @DisplayName("find by id")
    public void findById() throws Exception {
        //given
        final PostEntity postEntity = PostEntity.builder()
                .title("1번 제목")
                .content("1번 내용")
                .author("1번 저자")
                .thumbNail("1번 썸네일")
                .build();
        given(postRepository.findById(postEntity.getId())).willReturn(Optional.of(postEntity));
        PostResponseDto postResponseDto = postService.findById(postEntity.getId());
        assertEquals(postEntity.getTitle(), postResponseDto.getTitle());
        verify(postRepository).findById(anyInt());
    }

    @Test
    @DisplayName("find all")
    public void findAll() throws Exception {
        //given
        List<PostEntity> postEntityList = new ArrayList<>();
        final PostEntity postEntity = PostEntity.builder()
                .title("1번 제목")
                .content("1번 내용")
                .author("1번 저자")
                .thumbNail("1번 썸네일")
                .build();
        postEntityList.add(postEntity);
        given(postRepository.findAll()).willReturn(postEntityList);
        //when
        List<PostResponseDto> postResponseDtoList = postService.findAll();
        //then
        System.out.println(postEntityList.get(0).getTitle() + " : " +  postResponseDtoList.get(0).getTitle());
        assertEquals(postEntityList.get(0).getTitle(), postResponseDtoList.get(0).getTitle());
        verify(postRepository).findAll();
    }

    @Test
    @DisplayName("save")
    public void save() throws Exception {
        //given
        List<String> list = Stream.of("one", "two", "three").collect(Collectors.toList());
        final PostRequestDto postRequestDto = PostRequestDto.builder()
                .title("1번 제목")
                .content("1번 내용")
                .author("1번 저자")
                .thumbNail("1번 썸네일")
                .images(list)
                .build();
        PostEntity postEntity = postRequestDto.toEntity();
        given(postRepository.save(any(PostEntity.class))).willReturn(postEntity);
        //when
        PostEntity savedPostEntity = postService.save(postRequestDto);
        //then
        Assertions.assertNotNull(savedPostEntity);
        assertEquals(postRequestDto.getTitle(), savedPostEntity.getTitle());
        verify(postRepository).save(any(PostEntity.class));
    }

    @Test
    @DisplayName("update")
    public void update() throws Exception {
        //given
        List<String> list = Stream.of("one", "two", "three").collect(Collectors.toList());
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .title("제목 업데이트")
                .content("1번 내용")
                .author("1번 저자")
                .thumbNail("1번 썸네일")
                .images(list)
                .build();
        PostEntity postEntity = PostEntity.builder()
                .title("1번 제목")
                .content("1번 내용")
                .author("1번 저자")
                .thumbNail("1번 썸네일")
                .build();
        given(postRepository.findById(postEntity.getId())).willReturn(Optional.of(postEntity));
        given(postRepository.save(postEntity)).willReturn(postEntity);
        //when
        PostResponseDto postResponseDto = postService.update(postEntity.getId(), postRequestDto);
        System.out.println(postResponseDto.toString());
        //then
        assertEquals(postRequestDto.getTitle(), postEntity.getTitle());
        verify(postRepository).save(any(PostEntity.class));
    }

    @Test
    @DisplayName("delete")
    public void delete() throws Exception {
        //given
        final int id = 1;
        //when
        postService.delete(id);
        //then
        verify(postRepository, times(1)).deleteById(anyInt());
    }
    
    @Test
    public void verifyPassword() throws Exception {
        //given
        final String password = "passwordTest";
        //when
        boolean result = postService.verifyPassword(password);
        //then
        System.out.println("result: " + result);
    }
}