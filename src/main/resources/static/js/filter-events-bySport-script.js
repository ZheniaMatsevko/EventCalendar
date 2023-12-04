function filterEventsBySport(sportId) {
    var url = "/all-events";
    if (sportId !== "all") {
        url += "/bySport?id=" + sportId;
    }
    window.location.href = url;
}