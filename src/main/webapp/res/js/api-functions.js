function loadLinks()
{
    $.ajax({url: '/linkServlet'})
        .success(function(links) {
            _.each(links, function(link) {
                $('#links').append('<li>' + link.title + '</li>');
            });
        })
}