package UMC.campusNote.classSideNote.controller;

import UMC.campusNote.classSideNote.dto.ClassSideNoteRequest;
import UMC.campusNote.classSideNote.dto.ClassSideNoteResponse;
import UMC.campusNote.classSideNote.entity.ClassSideNote;
import UMC.campusNote.classSideNote.service.ClassSideNoteService;

import UMC.campusNote.common.ApiResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/class-side-note")
@AllArgsConstructor
public class ClassSideNoteController {

    ClassSideNoteService classSideNoteService;

    @PostMapping("/create/")
    public ApiResponse<ClassSideNoteResponse> createClassSideNote(
            @RequestParam Long userLessonId,
            @RequestBody ClassSideNoteRequest request) {
        ClassSideNote classSideNote = classSideNoteService.createClassSideNote(userLessonId, request);

        ClassSideNoteResponse response = convert(classSideNote);

        return ApiResponse.onSuccess(response);
    }


    @GetMapping("/{classSideNoteId}")
    public ApiResponse<ClassSideNoteResponse> getClassSideNoteById(
            @PathVariable Long classSideNoteId) {
        ClassSideNote classSideNote = classSideNoteService.getClassSideNoteById(classSideNoteId);

        ClassSideNoteResponse response = convert(classSideNote);

        return ApiResponse.onSuccess(response);

    }

    @GetMapping("/{userLessonId}/list")
    public ApiResponse<Collection<ClassSideNoteResponse>> getClassSideNoteListByUserLessonId(
            @PathVariable("userLessonId") Long userLessonId
    ) {
        List<ClassSideNote> classSideNotes =
                classSideNoteService.getClassSideNoteListByUserLessonId(userLessonId);

        List<ClassSideNoteResponse> classSideNoteResponseList = new ArrayList<>();
        for (ClassSideNote classSideNote : classSideNotes) {
            classSideNoteResponseList.add(convert(classSideNote));
        }
        return ApiResponse.onSuccess(classSideNoteResponseList);
    }

    @PutMapping("/{classSideNoteId}")
    public ApiResponse<ClassSideNoteResponse> updateClassSideNote(
            @PathVariable Long classSideNoteId,
            @RequestBody ClassSideNoteRequest request) {
        ClassSideNote classSideNote = classSideNoteService.updateClassSideNote(classSideNoteId, request);

        ClassSideNoteResponse response = convert(classSideNote);

        return ApiResponse.onSuccess(response);
    }

    @PatchMapping("/{classSideNoteId}")
    public ApiResponse<ClassSideNoteResponse> updateClassSideNoteContent(
            @PathVariable Long classSideNoteId,
            @RequestParam String content) {
        ClassSideNote classSideNote = classSideNoteService.getClassSideNoteById(classSideNoteId);
        classSideNote.updateContent(content);

        ClassSideNoteResponse response = convert(classSideNote);

        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/{classSideNoteId}")
    public ApiResponse<ClassSideNoteResponse> deleteClassSideNoteById(
            @PathVariable
            Long classSideNoteId) {
        ClassSideNote classSideNote = classSideNoteService.getClassSideNoteById(classSideNoteId);

        classSideNoteService.deleteById(classSideNoteId);

        ClassSideNoteResponse response = convert(classSideNote);

        return ApiResponse.onSuccess(response); // 삭제된 내용 반환


    }


    private ClassSideNoteResponse convert(ClassSideNote classSideNote) {
        return ClassSideNoteResponse.builder()
                .id(classSideNote.getId())
                .content(classSideNote.getContent())
                .isTodo(classSideNote.getIsTodo())
                .colorCode(classSideNote.getColorCode())
                .deadline(classSideNote.getDeadline() != null ?
                        classSideNote.getDeadline().toString() : null)
                .build();
    }
}