package com.dkb.shortener.service

import com.dkb.shortener.dto.UrlRequest
import java.util.Optional

interface UrlService {

    /**
     * API to get shortener URL.
     *
     * @param urlRequest UrlRequest.
     * @return String
     */
    fun getShortUrl(urlRequest: UrlRequest) : String
    /**
     * API to get a full URL.
     *
     * @param hash String.
     * @return String
    */
    fun getFullUrl(hash: String) : Optional<String>
}