package devlog.hong.service;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.repository.ImageRepository;
import devlog.hong.dto.ImageRequestDto;
import devlog.hong.dto.ImageResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    @Test
    @DisplayName("image save in db")
    public void save() throws Exception {
        //given
        List<String> list = Stream.of("one", "two", "three").collect(Collectors.toList());
        final ImageRequestDto imageRequestDto = ImageRequestDto.builder()
                .images(list)
                .build();
        List<ImageEntity> imageEntityList = imageRequestDto.toEntity();
        given(imageRepository.saveAll(any())).willReturn(imageEntityList);
        //when
        List<ImageResponseDto> imageResponseDtoList = imageService.save(imageRequestDto);
        //then
        System.out.println(imageResponseDtoList.toString());
        Assertions.assertNotNull(imageResponseDtoList);
        verify(imageRepository).saveAll(anyList());
    }

    @Test
    @DisplayName("image delete in db")
    public void delete() throws Exception {
        //given
        List<String> list = Stream.of("one", "two", "three").collect(Collectors.toList());
        final int id = 1;
        //when
        imageService.delete(list);
        //then
        verify(imageRepository, times(3)).deleteByImg(anyString());
    }
}