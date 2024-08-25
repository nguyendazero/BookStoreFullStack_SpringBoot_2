document.addEventListener('DOMContentLoaded', function () {
    var sidebar = document.getElementById('sidebar');
    document.getElementById('sidebarCollapse').addEventListener('click', function () {
        sidebar.classList.toggle('active');
        document.getElementById('content').classList.toggle('collapsed');
    });
});

function deleteBook(bookId) {
    if (confirm('Are you sure you want to delete this book?')) {
        window.location.href = '/admin/books/delete/' + bookId;
    }
}

function deleteOrder(orderId) {
    if (confirm('Are you sure you want to delete this order?')) {
        window.location.href = '/admin/orders/delete/' + orderId;
    }
}

function deleteUser(userId) {
    if (confirm('Are you sure you want to delete this user?')) {
        window.location.href = '/admin/users/delete/' + userId;
    }
}
