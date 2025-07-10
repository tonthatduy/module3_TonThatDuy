package com.example.lesson.service.lessontype;

import com.example.lesson.repository.lessontype.ILessonTypeRepository;
import com.example.lesson.repository.lessontype.LessonTypeRepository;

import java.util.List;

public class LessonTypeService implements ILessonTypeService {
    private ILessonTypeRepository lessonTypeRepository = new LessonTypeRepository();


    @Override
    public List<com.example.lesson.model.LessonType> findAll() {
        return lessonTypeRepository.findAll();
    }
}
