$(document).ready(function() {
    $('#inviteBtn').click(function(e){
        e.preventDefault();

        $('#invitationModalBody').load(contextPath + '/job/invitationCode', function(){
            $('#invitationModal').modal('show');
        });
    });
});
