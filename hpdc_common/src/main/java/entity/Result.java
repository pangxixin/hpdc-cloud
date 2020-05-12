package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Result {
    @NonNull private boolean flag;
    @NonNull private Integer code;
    @NonNull private String message;
    private Object data;
}
