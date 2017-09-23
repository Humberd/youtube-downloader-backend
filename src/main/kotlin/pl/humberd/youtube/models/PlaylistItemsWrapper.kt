package pl.humberd.youtube.models

data class PlaylistItemsWrapper(
        val nextPageToken: String,
        val videoIds: List<String>
)