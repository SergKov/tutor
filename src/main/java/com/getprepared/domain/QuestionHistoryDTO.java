package com.getprepared.domain;

/**
 * Created by koval on 02.01.2017.
 */
public class QuestionHistoryDTO extends AbstractDTO {

    private final ResultDTO resultDTO;
    private final String text;
    private final AnswerType type;

    public QuestionHistoryDTO(Long id, ResultDTO resultDTO, String text, AnswerType type) {
        super(id);
        this.resultDTO = resultDTO;
        this.text = text;
        this.type = type;
    }

    public ResultDTO getResultDTO() {
        return resultDTO;
    }

    public String getText() {
        return text;
    }

    public AnswerType getType() {
        return type;
    }
}
