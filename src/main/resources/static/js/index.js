$.ajax({
    url: "/api/boardlist",
    type: "GET",
    dataType: "JSON",
    cache: false,
    async: false,
    success: function (data) {
        data.forEach(d => {
            let sendStr = "<h4 onClick=\"toBoard(this)\">"  + d.boardName + "</h4></br>";
            $("#boardlist").append(sendStr);
        });
    }
});

function toBoard(item) {
    window.location = '/' + $(item).text();
}