package com.example.githubtest.data.network

object GenerateHeaders {

    fun getHeaders(requestContentLength : Long?) : List<NetworkRequestHeader> {

        val arrayList = ArrayList<NetworkRequestHeader>()
        arrayList.addAll(
            listOf(
                NetworkRequestHeader("Authorization" ,"Bearer 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") ,
            )
        )

        return arrayList
    }


}