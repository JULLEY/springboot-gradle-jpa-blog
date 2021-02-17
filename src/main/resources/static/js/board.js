let index = {
    init : function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });
        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
    },
    save : function(){
        // alert("save함수 호출 TEST");
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body
            contentType: "application/json; charset=utf-8", // body데이터 타입
            dataType: "json"    // 요청에 대한 서버의 응답이 왔을때 기본적으로 모든것이 문자열인데 JSON처럼 생겼으면 => javascript object로바꿔준다
        }).done(function (res) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/board/list";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    deleteById : function(){
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.href = "/board/list";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        let id = $("#id").val();
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("글수정이 완료되었습니다.");
            location.href = "/board/list";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    replySave : function(){
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        }

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    replyDelete : function(boardId, replyId){

        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function(resp){
            alert("댓글삭제가 완료되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
}

index.init();