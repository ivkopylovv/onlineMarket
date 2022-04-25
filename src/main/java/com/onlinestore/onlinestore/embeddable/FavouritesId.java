package com.onlinestore.onlinestore.embeddable;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FavouritesId implements Serializable {
    private Long userId;
    private Long productId;
}
