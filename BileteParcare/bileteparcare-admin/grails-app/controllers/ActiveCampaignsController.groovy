import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.*;
 
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.output.*;


class ActiveCampaignsController {

	
     def index = { 
     
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat('dd-MM-yyyy HH:mm')
        
     
        FastReportBuilder drb = new FastReportBuilder();
        drb = drb.addColumn("Name","campaignName",String.class.getName(),30);
        drb = drb.addColumn("To","campaignTo",String.class.getName(),30);
        drb = drb.addColumn("Discount","discountPercentage",String.class.getName(),15);
        drb = drb.addColumn("Remaining days","leftDays",String.class.getName(),15);
        drb = drb.addColumn("Details","details",String.class.getName(),70);
        
        DynamicReport dr = drb.setTitle("Campanii active la data " + formatter.format(new Date())).setUseFullPageWidth(true).build();
        JRDataSource ds = new JRBeanCollectionDataSource(ActiveCampaign.list());   
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, 
                new ClassicLayoutManager(), ds);
 
        def reportFormat = 'PDF';
        def reportFileName = "ActiveCampaigns";
        ReportWriter reportWriter = ReportWriterFactory.getInstance().getReportWriter(jp, reportFormat, [(JRHtmlExporterParameter.IMAGES_URI): "${request.contextPath}/report/image?image=".toString()]  );
        if (reportFileName) {
            response.addHeader('content-disposition', "attachment; filename=${reportFileName}.${reportFormat.toLowerCase()}")
        }
        
        reportWriter.writeTo(response)
    }

}
