package br.com.fiap.chatia.dto.response;

public class QuestionarioResponse {
    private String resultadoIA;

    public QuestionarioResponse() {
    }

    public QuestionarioResponse(String resultadoIA) {
        this.resultadoIA = resultadoIA;
    }

    public String getResultadoIA() {
        return resultadoIA;
    }


}
