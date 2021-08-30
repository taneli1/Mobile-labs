package com.example.retrofit.data.retrofit.wiki

data class WikiResponse(
    val query: WikiQueryResponse
) {}

data class WikiQueryResponse(
    val searchinfo: WikiSearchInfo,
    val search: List<WikiSearchResponseData>
) {}

data class WikiSearchInfo(
    val totalhits: Int,
    val suggestion: String,
) {}

data class WikiSearchResponseData(
    val ns: Int,
    val title: String,
    val pageid: Int,
    val size: Int,
    val wordcount: Int,
    val snippet: String,
    val timestamp: String,
)