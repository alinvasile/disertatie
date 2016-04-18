Ext.onReady(function(){

        // NOTE: This is an example showing simple state management. During development,
        // it is generally best to disable state management as dynamically-generated ids
        // can change across page loads, leading to unpredictable results.  The developer
        // should ensure that stable state ids are set for stateful components in real apps.
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        
       var viewport = new Ext.Viewport({
            layout:'border',
            items:[
                new Ext.BoxComponent({ // raw
                    region:'north',
                    el: 'north',
                    height:32
                }),{
                    region:'south',
                    contentEl: 'south',
                    split:true,
                    height: 70,
                    minSize: 70,
                    maxSize: 100,
                    collapsible: false,
                    title:'',
                    margins:'0 0 0 0'
                }, {
                    region:'east',
                    title: 'Release details',
                    collapsible: true,
                    split:true,
                    width: 225,
                    minSize: 175,
                    maxSize: 400,
                    layout:'fit',
                    margins:'0 5 0 0',
                    items:
                        new Ext.TabPanel({
                            border:false,
                            activeTab:0,
                            tabPosition:'bottom',
                            items:[
                            new Ext.grid.PropertyGrid({
                                title: 'Property Grid',
                                closable: true,
                                source: {
                                    "(name)": "Layout version",
                                    "grouping": false,
                                    "autoFitColumns": true,
                                    "productionQuality": true,
                                    "created": new Date(Date.parse('June 1 2009')),
                                    "tested": true,
                                    "version": "1.0",
                                    "borderWidth": 1
                                }
                            })]
                        })
                 },{
                    region:'west',
                    id:'west-panel',
                    title:'Administrare servicii',
                    split:true,
                    width: 200,
                    minSize: 175,
                    maxSize: 400,
                    collapsible: true,
                    margins:'0 0 0 5',
                    layout:'accordion',
                    layoutConfig:{
                        animate:true
                    },
                    items: [{
                        contentEl: 'west-campaign',
                        title:'Campanii si reduceri',
                        border:false,
                        iconCls:'settings'
                    },{
                    	contentEl: 'west-tariff',
                        title:'Tarife',                        
                        border:false,
                        iconCls:'settings'
                    },{
                        contentEl: 'west-parking',
                        title:'Parcari',                        
                        border:false,
                        iconCls:'settings'
                    },{
                    	contentEl: 'west-reports',
                        title:'Rapoarte',                        
                        border:false,
                        iconCls:'settings'
                    },{
                    	contentEl: 'west-users',
                        title:'Securitate',                        
                        border:false,
                        iconCls:'settings'
                    }]
                },
                new Ext.TabPanel({
                    region:'center',
                    deferredRender:false,
                    activeTab:0,
                    items:[{
                        contentEl:'center1',
                        title: 'Center Panel',
                        autoScroll:true
                    }]
                })
             ]
        });
        Ext.get("hideit").on('click', function() {
           var w = Ext.getCmp('west-panel');
           w.collapsed ? w.expand() : w.collapse(); 
        });
    });