function showConfirmationModal(type,name,id) {
    var modalBody = document.getElementById('modalBody');
    modalBody.innerHTML = 'Are you sure you want to delete '+name+'?';

    $('#confirmDelete').click(function () {
        window.location.href = '/all-'+type+'/delete/' + id;
    });

    $('#confirmationModal').modal('show');
}