package br.org.flem.sac.quartz;

import br.org.flem.commons.util.PropertiesUtil;
import br.org.flem.fw.email.EmailBuilder;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.util.Data;
import br.org.flem.sac.bo.AditivoBO;
import br.org.flem.sac.bo.ContratoBO;
import br.org.flem.sac.negocio.Aditivo;
import br.org.flem.sac.negocio.Contrato;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 *
 * @author mccosta
 */
public class VerificaFinalVigencia implements Job {

    @Override
    public void execute(JobExecutionContext arg0) {

        try {

            this.enviarEmail(lerEmails());

        } catch (IOException ex) {
            Logger.getLogger(VerificaFinalVigencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] lerEmails() throws IOException {
        return PropertiesUtil.getProperty("email").trim().split(";");
    }

    public void enviarEmail(String[] destinatarios) {
        try {
            Collection<Contrato> lista = verificaContratosAVencer();
            StringBuilder texto = new StringBuilder();
            Aditivo aditivo;
            
            if (lista != null && lista.size() > 0) {
                HtmlEmail email = new EmailBuilder().getHelpDeskEmail();

                for (String destinatario : destinatarios) {
                    email.addTo(destinatario);
                }

                email.setSubject("Contratos com data final de Vigência se aproximando");

                texto.append(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date()) + "\n");
                
                //texto.append("\n\n DESENVOLVIMENTO \n\n");
                
                texto.append("Os seguintes contratos econtram-se com sua vigência acabando :\n\n");

                for (Contrato c : lista) {
                    texto.append(c.getDescricaoCompleta()).append("\n");
                    
                    aditivo = new AditivoBO().obterUltimoPorContrato(c);
                    if(aditivo != null){
                        texto.append("Vigência: ")
                                .append(Data.formataData(aditivo.getInicioVigencia()))
                                .append(" - ").append(Data.formataData(aditivo.getFimVigencia())).append("\n");
                    }else{
                        texto.append("Vigência: ").append(Data.formataData(c.getInicioVigencia()))
                                .append(" - ")
                                .append(Data.formataData(c.getFimVigencia()))
                                .append("\n");
                    }
                }
                email.setMsg(texto.toString());
                email.send();
            }

        } catch (AplicacaoException ex) {
            Logger.getLogger(VerificaFinalVigencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
            Logger.getLogger(VerificaFinalVigencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Collection<Contrato> verificaContratosAVencer() {
        Collection<Contrato> lista = new ArrayList<Contrato>();
        try {
            Date hoje = new Date();

            for (Contrato contrato : new ContratoBO().obterTodos()) {
                Boolean vigente = contrato.getSituacao() == null || contrato.getSituacao().equalsIgnoreCase("VIGENTE");
                if(contrato.getAviso() && contrato.getLimiteAviso() != null && hoje.after(contrato.getLimiteAviso()) && vigente){
                    lista.add(contrato);
                }
            }
        } catch (AplicacaoException ex) {
            Logger.getLogger(VerificaFinalVigencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }
}
