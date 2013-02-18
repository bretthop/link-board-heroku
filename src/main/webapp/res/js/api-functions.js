function loadLinks()
{
    fetchTmpl(LINK_TMPL_URL, function(tmpl) {
        $.ajax({url: '/linkServlet'})
            .success(function(links) {
                _.each(links, function(link) {
                    var rendered = _.template(tmpl, {link: link});

                    $('#links').append(rendered);
                });
            })
    });
}