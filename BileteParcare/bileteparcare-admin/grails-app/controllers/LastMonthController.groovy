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


class LastMonthController {

    def index = { 
    
    	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat('dd-MM-yyyy')
        
     
        FastReportBuilder drb = new FastReportBuilder();
        drb = drb.addColumn("Reservation date ","reservationDate",String.class.getName(),10);
        drb = drb.addColumn("Vehicle number","vehicleNumber",String.class.getName(),10);
        drb = drb.addColumn("Interval","reservationInterval",String.class.getName(),10);
        drb = drb.addColumn("Price","price",String.class.getName(),15);
       
        DynamicReport dr = drb.setTitle("Total rezervari pentru ultimele 30 zile. Pana la \\n" + formatter.format(new Date(System.currentTimeMillis() - 1000 * 60 *60 *24))).setUseFullPageWidth(true).build();
        JRDataSource ds = new JRBeanCollectionDataSource(LastMonthCharges.list());   
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, 
                new ClassicLayoutManager(), ds);
 
        def reportFormat = 'PDF';
        def reportFileName = "Charges";
        ReportWriter reportWriter = ReportWriterFactory.getInstance().getReportWriter(jp, reportFormat, [(JRHtmlExporterParameter.IMAGES_URI): "${request.contextPath}/report/image?image=".toString()]  );
        if (reportFileName) {
            response.addHeader('content-disposition', "attachment; filename=${reportFileName}.${reportFormat.toLowerCase()}")
        }
        
        reportWriter.writeTo(response)
    }
}
