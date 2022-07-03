package com.dkb.shortener.service

import com.dkb.shortener.dto.UrlRequest
import com.dkb.shortener.exception.DataValidationException
import com.dkb.shortener.model.Url
import com.dkb.shortener.repository.UrlRepository
import com.dkb.shortener.util.ErrorMessage
import org.apache.commons.validator.routines.UrlValidator
import org.hashids.Hashids
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Optional

@Service
class UrlServiceImpl(
            private val urlRepository: UrlRepository,
            private val hashids: Hashids,
            private val validator: UrlValidator) : UrlService {

    override fun getShortUrl(urlRequest: UrlRequest): String {
        if (!validator.isValid(urlRequest.url)) {
            throw DataValidationException(ErrorMessage.invalidUrl)
        }
        val url = Url()
        url.url = urlRequest.url
        url.createdDate = LocalDate.now()
        return hashids.encode(urlRepository.save(url).id)
    }

    override fun getFullUrl(hash: String): Optional<String> {
        if (hashids.decode(hash).isEmpty()) {
            return Optional.empty<String>()
        }
        val base : Optional<Url> = urlRepository.findById(hashids.decode(hash).first())
        if (base.isEmpty) {
            return Optional.empty<String>()
        }
        return Optional.of(base.get().url)
    }

}