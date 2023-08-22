package com.lucas.yugiohcards.adapter.out.ygoclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CheckDatabaseVersionResponse implements Serializable {

    @JsonProperty("database_version")
    String dataBaseVersion;

    @JsonProperty("last_update")
    String lastUpdate;

}
