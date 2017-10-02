var menuData = {};

function loadJson(url, target) {
    $.getJSON(url, function(data) {
        menuData = data;
        $('#' + target).text(JSON.stringify(menuData, null, 2));

        loadItems('entrees', menuData.entrees);
        loadItems('veggies', menuData.veggies);
        loadItems('treats', menuData.treats);

        $('.remove-item').each(function(i, e) {
            $(e).click(function() {
                $(this).closest('tr').remove();
            });
        });

        $('.add-item').each(function(i, e) {
            $(e).click(function() {
                var itemType = $(this).attr('data-item-type');
                var item = createItem($(`table[data-item-type="${itemType}"]`).attr('id'), 'item1', itemType, 'description1');
                item.find('.x-editable').each(function(i, e2) {
                    $(e2).editable();
                });
                item.find('.remove-item').each(function(i, e2) {
                    $(e2).click(function() {
                        $(this).closest('tr').remove();
                    });
                });                
            })
        });

        $.fn.editable.defaults.mode = 'inline';

        $('.x-editable').each(function(i, e) {
            $(e).editable();
        });
    });
}

function loadItems(target, items) {
    items.forEach(function(item) {
        createItem(target, item.id, item.type, item.description);
    });
}

function createItem(target, id, type, description) {
    var html = `<tr id="${id}">`;
    html += `<td class="item-id" data-field="id"><a href='#' class='x-editable' data-type='text' data-pk='${id}'>${id}</a></td>`;
    html += `<td class="item-description" data-field="description"><a href='#' class='x-editable' data-type='text' data-pk='${id}'>${description}</a></td>`;
    html += `<td class="item-action"><button type="button" class="btn remove-item" data-item-type="${type}" data-item-id="${id}">Remove</button></td>`;
    html += '</tr>';
    return $(`#${target} > tbody`).append(html);
}

$(document).ready(function() {
    loadJson('https://lambda-function-bucket-us-east-1-1486176755721.s3.amazonaws.com/SaintColumban/menu.json', 'json');
});