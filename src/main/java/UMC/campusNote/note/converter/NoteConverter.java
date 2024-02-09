package UMC.campusNote.note.converter;

import UMC.campusNote.mapping.UserLessonNote;
import UMC.campusNote.note.dto.NoteResponseDTO;
import UMC.campusNote.note.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteConverter {

    public static NoteResponseDTO.NoteCreateDTO toNoteCreateDTO(Note note){
        return NoteResponseDTO.NoteCreateDTO.builder()
                .noteId(note.getId())
                .build();
    }

    public static NoteResponseDTO.NoteGetDTO toNoteGetDTO(UserLessonNote userLessonNote){
        return NoteResponseDTO.NoteGetDTO.builder()
                .noteId(userLessonNote.getNote().getId())
                .noteName(userLessonNote.getNote().getNoteName())
                .build();
    }
}
