/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function abc() {
    Swal.fire(
            'Good job!',
            'You clicked the button!',
            'success'
            );
}
function showError(content) {
    Swal.fire(
        'Opps!',
        content,
        'error'
    );
}
function showSuccess(content) {
    Swal.fire(
        'OK!',
        content,
        'success'
    );
}

