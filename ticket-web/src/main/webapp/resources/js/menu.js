jQuery(document).ready(function()
{
  var remembered = getCookie("myPanelMenu_remembered");
  var rememberedI = getCookie("myPanelMenu_rememberedI");
  var itemSelI = getCookie("myPanelMenu_itemSel");
  PF('myPanelMenu').headers.each(function()
  {
    var header = jQuery(this);
    if(header.text() === remembered && window.location.pathname !== "/ticket-web/ticketMain.jsf")
    {
      PF('myPanelMenu').expandRootSubmenu(header, false);
    }
    else
    {
      PF('myPanelMenu').collapseRootSubmenu(header);
      header.removeClass('ui-state-hover');
    }
  });
  PF('myPanelMenu').treeLinks.each(function()
  {
    var item = jQuery(this);
    if(item.text() !== rememberedI)
    {
      PF('myPanelMenu').collapseRootSubmenu(item);
      item.removeClass('ui-state-hover');
    }
    else
    {
      PF('myPanelMenu').expandRootSubmenu(item, false);
    }
  });
  
  PF('myPanelMenu').menuitemLinks.each(function()
  {
    var item = jQuery(this);
    if(item.text() == itemSelI && window.location.pathname !== "/ticket-web/ticketMain.jsf")
    {
    	item.css("cssText","text-decoration: underline !important; color:#003e5f !important; font-weight: bold !important; background-color: #abd6fc !important;");
    }
  });
  
  PF('myPanelMenu').menuitemLinks.click(function()
  {
    var itemSel = jQuery(this);
    setCookie("myPanelMenu_itemSel", itemSel.text());
	var expanded = getCookie("myPanelMenu_expanded");
    setCookie("myPanelMenu_remembered", expanded);
    var expandedI = getCookie("myPanelMenu_expandedI");
    setCookie("myPanelMenu_rememberedI", expandedI);
  });
  PF('myPanelMenu').headers.click(function()
  {
    var currHeader = jQuery(this);
    PF('myPanelMenu').headers.each(function()
    {
      var header = jQuery(this);
      if(header.text() !== currHeader.text())
      {
        PF('myPanelMenu').collapseRootSubmenu(header);
        header.removeClass('ui-state-hover');
      }
    });
    setCookie("myPanelMenu_expanded", currHeader.text());
  })
  PF('myPanelMenu').treeLinks.click(function()
  {
    var currItem = jQuery(this);
    PF('myPanelMenu').treeLinks.each(function()
    {
      var item = jQuery(this);
      if(item.text() !== currItem.text())
      {
    	PF('myPanelMenu').collapseRootSubmenu(item);
        item.removeClass('ui-state-hover');
      }
      else
      {
    	currItem.addClass('ui-state-hover');
      }
    });
    setCookie("myPanelMenu_expandedI", currItem.text());
  })
});
function setCookie(cname, cvalue, exdays) {
  exdays = typeof exdays !== 'undefined' ? exdays : 1;
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + "; " + expires+ "; path=/";
}
function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') c = c.substring(1);
      if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
  }
  return "";
}