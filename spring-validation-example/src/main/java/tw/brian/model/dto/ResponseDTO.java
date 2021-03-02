package tw.brian.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/8
 */
@Data
public class ResponseDTO<T> {
    private Boolean success;

    private String msg;

    private OffsetDateTime responseTime;

    private String title;

    private T data;

    private ResponseDTO(Builder<T> builder) {
        this.success = builder.success;
        this.msg = builder.msg;
        this.responseTime = OffsetDateTime.now();
        this.title = builder.title;
        this.data = builder.data;
    }

    public static <T> Builder<T> createSuccessBuilder() {
        return ResponseDTO.<T>createBuilder()
            .setSuccess(true);
    }

    public static <T> Builder<T> createFailureBuilder() {
        return ResponseDTO.<T>createBuilder()
            .setSuccess(false);
    }

    public static <T> ResponseDTO<T> createFailureResponse() {
        return ResponseDTO.<T>createBuilder()
            .setSuccess(false)
            .build();
    }

    public static <T> ResponseDTO<T> createSuccessResponse() {
        return ResponseDTO.<T>createBuilder()
            .setSuccess(true)
            .build();
    }

    public static ResponseDTO<Void> createVoidSuccessResponse() {
        return ResponseDTO.<Void>createBuilder()
            .setSuccess(true)
            .build();
    }

    public static ResponseDTO<Void> createVoidFailureResponse() {
        return ResponseDTO.<Void>createBuilder()
            .setSuccess(false)
            .build();
    }

    public static <T> Builder<T> createBuilder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private Boolean success;
        private String msg;
        private String title;
        private T data;

        public Builder() { }

        public Builder(ResponseDTO<T> copy) {
            this.success = copy.success;
            this.msg = copy.msg;
            this.data = copy.data;
        }

        public Builder<T> setSuccess(Boolean val) {
            success = val;
            return this;
        }

        public Builder<T> setMsg(String val) {
            msg = val;
            return this;
        }

        public Builder<T> setTitle(String val) {
            title = val;
            return this;
        }

        public Builder<T> setData(T val) {
            data = val;
            return this;
        }

        public ResponseDTO<T> build() {
            return new ResponseDTO<>(this);
        }
    }
}
