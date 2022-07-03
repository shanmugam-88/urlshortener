package com.dkb.shortener.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation

@ApiOperation(value = "URL shortener request object")
@ApiModel
data class UrlRequest(
    @ApiModelProperty(required = true, notes = "Url to be shortened.")
    @JsonProperty("url") var url: String)