package pl.humberd.youtube.models

data class PlayListItems(
        val kind: String,
        val etag: String,
        val prevPageToken: String?,
        val nextPageToken: String?,
        val pageInfo: PageInfo,
        val items: Array<PlayListItem>
)

data class PageInfo(
        val totalResults: String,
        val resultsPerPage: Int
)

data class PlayListItem(
        val kind: String,
        val etag: String,
        val id: String,
        val snippet: Snippet
)

data class Snippet(
        val publishedAt: String,
        val channelId: String,
        val title: String,
        val description: String,
        val channgelTitle: String,
        val playlistId: String,
        val position: Int,
        val resourceId: Resource
)

data class Resource(
        val kind: String,
        val videoId: String
)
