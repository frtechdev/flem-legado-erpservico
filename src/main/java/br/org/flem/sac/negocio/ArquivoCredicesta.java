package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author FCSilva
 */
@Entity
@Table(name="arquivocredicesta")
public class ArquivoCredicesta extends BaseDTOAb implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arquivocredicesta")
    private Integer id;

    @Lob
    @Column(nullable = false)
    private byte[] arquivo;

    @Column(length=100)
    private String nome;
    
    @Column(name="datareferencia")
    @Temporal(TemporalType.DATE)
    private Date dataReferencia;    

    @Column(name="datagravacao")
    @Temporal(TemporalType.DATE)
    private Date dataGravacao;

    public ArquivoCredicesta() {
    }

    public Serializable getPk() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataGravacao() {
        return dataGravacao;
    }

    public void setDataGravacao(Date dataGravacao) {
        this.dataGravacao = dataGravacao;
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }
}
