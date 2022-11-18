package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.LabelStudioAction;
import org.tlbc.hymns.model.Song;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class LabelStudioService {

    @Resource
    private SongService songService;

    private enum ACTION {
        ANNOTATION_CREATED, ANNOTATION_UPDATED, TASKS_CREATED, TASKS_DELETED, LABEL_LINK_CREATED
    }

    public Song process(LabelStudioAction action) {
        if (ACTION.TASKS_CREATED.name().equals(action.getAction())) {
            createTasks(action);
        } else if (ACTION.TASKS_DELETED.name().equals(action.getAction())) {
            deleteTask(action);
        } else if (ACTION.ANNOTATION_CREATED.name().equals(action.getAction())) {
            createAnnotation(action);
        } else if (ACTION.ANNOTATION_UPDATED.name().equals(action.getAction())) {
            updateAnnotation(action);
        } else if (ACTION.LABEL_LINK_CREATED.name().equals(action.getAction())) {
            // ignore
            log.info("ignore LABEL_LINK_CREATED");
        } else {
            log.error("Illegal action: {}", action.getAction());
        }
        return new Song(123, "中文");
    }
    public void createTasks(LabelStudioAction action) {
        for (LabelStudioAction.LabelStudioTask task : action.getTasks()) {
            Song song = new Song(task.getId(), task.getData().getText());
            Song savedSong = songService.save(song);
            log.debug("savedSong: {}", savedSong);
        }
    }
    public void deleteTask(LabelStudioAction action) {
        for (LabelStudioAction.LabelStudioTask task : action.getTasks()) {
            songService.delete(task.getId());
            log.debug("song deleted, id: {}", task.getId());
        }
    }

    public void createAnnotation(LabelStudioAction action) {
        Song song = songService.getById(action.getTask().getId()).orElseGet(() -> {
            log.debug("ready to add song, id:{}, name:{}", action.getTask().getId(), action.getTask().getData().getText());
            Song newSong = new Song(action.getTask().getId(), action.getTask().getData().getText());
            Song savedSong = songService.save(newSong);
            log.debug("savedSong: {} when creating annotation", savedSong);
            return savedSong;
        });
        song.setLabels(getNewLabels(action));
        Song savedSong = songService.save(song);
        log.debug("savedSong: {} when creating annotation", savedSong);
    }
    public void updateAnnotation(LabelStudioAction action) {
        Song song = songService.getById(action.getTask().getId()).orElseThrow();
        song.setLabels(getNewLabels(action));
        Song savedSong = songService.save(song);
        log.debug("savedSong: {} when updating annotation", savedSong);
    }

    private static List<String> getNewLabels(LabelStudioAction action) {
        List<LabelStudioAction.LabelStudioAnnotation.AnnotationResult> results = action.getAnnotation().getResult();
        if (results.size() > 0) {
            List<List<String>> taxonomy = results.get(0).getValue().getTaxonomy();
            List<String> labels = new ArrayList<>();
            for (List<String> labelCombination : taxonomy) {
                labels.add(labelCombination.get(1));
            }
            return labels;
        } else {
            return List.of();
        }
    }
}
