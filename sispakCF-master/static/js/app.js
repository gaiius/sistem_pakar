var MODE_INSERT = 100;
var MODE_UPDATE = 200;

function appInit()
{

}

function request(path, param, fx)
{
    var url = BASE_PATH + '?q=' + path;
    contentOverlay();
    $.post(url, param, function(r){
        hideContentOverlay();
        if(typeof fx == 'function')
            fx(r);
    }, 'json');
}

function contentOverlay()
{
        var obj = $('#webBody');
        var pos = $(obj).offset();
        var h = $(obj).outerHeight();
        var w = $(obj).outerWidth();
        var d = $('<div>');
        $(d)
        .addClass('loadingOverlay')
        .addClass('ui-widget-overlay')
        .css({
                position: 'absolute',
                left: pos.left,
                top: pos.top,
                width: w,
                height: h,
                zIndex: maxZ()
        })
        .appendTo('body');
        
        var spinOpt = {
                lines: 12, // The number of lines to draw
                length: 7, // The length of each line
                width: 5, // The line thickness
                radius: 10, // The radius of the inner circle
                color: '#333', // #rbg or #rrggbb
                speed: 1, // Rounds per second
                trail: 100, // Afterglow percentage
                shadow: false // Whether to render a shadow
        };
        $(d).spin(spinOpt);
}

function hideContentOverlay()
{
        $('.loadingOverlay').remove();
}

// =============
function maxZ()
{
    var m = [0];
    $('body > *').each(function(){
        if(/position|fixed/.test($(this).css('position')))
        {
            var z = parseInt($(this).css('z-index'));
            if(isNaN(z)) z = 0
            m.push(z);
        }
    });

    return Math.max.apply(null, m);
}

function showModal(title, comp, buttons, closable)
{
    var d = $('<div>').addClass('modal hide fade').appendTo('body');
    var head = $('<div>').addClass('modal-header').appendTo(d);

    if(typeof closable == 'undefined')
    {
        $('<button>').addClass('close')
            .attr('data-dismiss', 'modal').attr('aria-hidden', true).text('x').appendTo(head);
    }
    else
    {
        $(d).attr('data-backdrop', 'static');
    }

    $('<h3>').appendTo(head).text(title);

    var body = $('<div>').addClass('modal-body').appendTo(d);

    if(! comp.jquery)
    {
        $(body).html(comp);
    }
    else
    {
        $(comp).data('pre-parent', $(comp).parents().get(0));
        $(comp).appendTo(body).show();
    }

    var footer = $('<div>').addClass('modal-footer').appendTo(d);
    if($.isPlainObject(buttons))
        buttons = [ buttons ];

    if($.isArray(buttons) && buttons.length > 0)
    {
        for(var i in buttons)
        {
            var button = buttons[i];
            var btn = $('<button>').appendTo(footer).text(button.text);
            if(typeof button.cls != 'undefined')
                $(btn).addClass('btn ' + button.cls);
            else
                $(btn).addClass('btn btn-primary');

            if($.isFunction(button.fx))
                $(btn).data('fx', button.fx).click(function(){
                    $(this).data('fx')(d);
                });
        }
    }

    $(d)
        .on('hidden', function(){
            if(comp.jquery)
            {
                $(comp).appendTo(
                        $( $(comp).data('pre-parent') )
                    )
                    .hide();
            }
            $(this).remove();
        })
        .modal();

    var z = maxZ();
    $('.modal-backdrop:last').css({
        zIndex: ++z
    });

    $(d).css('z-index', ++z);

    return d;
}

window.alert = function(str, fx){
    showModal('Informasi', str, {
        text: 'OK',
        fx: function(modal){
            if($.isFunction(fx))
                fx();

            $(modal).modal('hide');
        }
    })
}

window.confirm = function(str, okFx, cancelFx){
    showModal('Konfirmasi', str, [
        {
            text: 'Ya',
            fx: function(modal){
                if($.isFunction(okFx))
                    okFx();

                $(modal).modal('hide');
            }
        },
        {
            text: 'Tidak',
            cls: 'btn-danger',
            fx: function(modal){
                if($.isFunction(cancelFx))
                    cancelFx();

                $(modal).modal('hide');
            }
        }
    ]
    )
}

window.prompt = function(msg, fx)
{
    var box = $('<textarea>').css('width', '514px');
    showModal(msg, box, [
        {
            text: 'Okay',
            fx: function(modal){
                if($.isFunction(fx))
                    fx($(box).val());

                $(modal).modal('hide');
            }
        },
        {
            text: 'Cancel',
            cls: 'btn-danger',
            fx: function(modal){
                $(modal).modal('hide');
            }
        }
    ]
    )
}

//==============

/* Bootstrap data paging */
$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
    return {
        "iStart":         oSettings._iDisplayStart,
        "iEnd":           oSettings.fnDisplayEnd(),
        "iLength":        oSettings._iDisplayLength,
        "iTotal":         oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage":          oSettings._iDisplayLength === -1 ?
            0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
        "iTotalPages":    oSettings._iDisplayLength === -1 ?
            0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
    };
};


/* Bootstrap pagination style */
$.extend( $.fn.dataTableExt.oPagination, {
    "bootstrap": {
        "fnInit": function( oSettings, nPaging, fnDraw ) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function ( e ) {
                e.preventDefault();
                if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
                    fnDraw( oSettings );
                }
            };

            $(nPaging).addClass('pagination').append(
                '<ul>'+
                    '<li class="prev disabled"><a href="#">&larr; '+oLang.sPrevious+'</a></li>'+
                    '<li class="next disabled"><a href="#">'+oLang.sNext+' &rarr; </a></li>'+
                    '</ul>'
            );
            var els = $('a', nPaging);
            $(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
            $(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
        },

        "fnUpdate": function ( oSettings, fnDraw ) {
            var iListLength = 5;
            var oPaging = oSettings.oInstance.fnPagingInfo();
            var an = oSettings.aanFeatures.p;
            var i, ien, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

            if ( oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
            }
            else if ( oPaging.iPage <= iHalf ) {
                iStart = 1;
                iEnd = iListLength;
            } else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
            }

            for ( i=0, ien=an.length ; i<ien ; i++ ) {
                // Remove the middle elements
                $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                // Add the new list items and their event handlers
                for ( j=iStart ; j<=iEnd ; j++ ) {
                    sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
                    $('<li '+sClass+'><a href="#">'+j+'</a></li>')
                        .insertBefore( $('li:last', an[i])[0] )
                        .bind('click', function (e) {
                            e.preventDefault();
                            oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
                            fnDraw( oSettings );
                        } );
                }

                // Add / remove disabled classes from the static elements
                if ( oPaging.iPage === 0 ) {
                    $('li:first', an[i]).addClass('disabled');
                } else {
                    $('li:first', an[i]).removeClass('disabled');
                }

                if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
                    $('li:last', an[i]).addClass('disabled');
                } else {
                    $('li:last', an[i]).removeClass('disabled');
                }
            }
        }
    }
} );


/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ( $.fn.DataTable.TableTools ) {
    $.extend( true, $.fn.DataTable.TableTools.classes, {
        "container": "DTTT btn-group",
        "buttons": {
            "normal": "btn",
            "disabled": "disabled"
        },
        "collection": {
            "container": "DTTT_dropdown dropdown-menu",
            "buttons": {
                "normal": "",
                "disabled": "disabled"
            }
        },
        "print": {
            "info": "DTTT_print_info modal"
        },
        "select": {
            "row": "active"
        }
    } );

    // Bootstrap dropdown
    $.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
        "collection": {
            "container": "ul",
            "button": "li",
            "liner": "a"
        }
    } );
}

$.extend(true, $.fn.dataTable.defaults, {
    bLengthChange: false,
    iDisplayLength: 6,
    sPaginationType: 'bootstrap',
    sDom: "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>"
});

$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );

$.extend(true, $.fn.dataTable.defaults.oLanguage, {
    sSearch: 'Cari ',
    sNext: 'Berikutnya',
    sPrevious: 'Sebelumnya',
    sInfoEmpty: 'Tidak ada data tersedia',
    sInfo: 'Data ke _START_ s/d _END_ dari total _TOTAL_',
    sInfoFiltered: '(hasil pencarian dari _MAX_ data)',
    sZeroRecords: 'Tidak data yang sesuai dengan pencarian',
    sEmptyTable: '<em>belum ada data tersedia</em>'
});

$.extend(true, $.fn.dataTable.defaults.oLanguage.oPaginate, {
    sFirst: 'Awal',
    sLast: 'Akhir',
    sNext: 'Berikutnya',
    sPrevious: 'Sebelumnya'
});