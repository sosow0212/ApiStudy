let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        })
        console.log("JS 실행")
    },

    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type: "POST",
            url: "/api/upload",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("저장완료");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}
index.init();