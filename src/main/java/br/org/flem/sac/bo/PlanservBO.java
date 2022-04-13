package br.org.flem.sac.bo;

import br.org.flem.commons.util.MoedaUtil;
import br.org.flem.commons.util.PropertiesUtil;
import br.org.flem.fw.enums.VerbasPlanservEnum;
import br.org.flem.fw.persistencia.dto.Departamento;
import br.org.flem.fw.persistencia.dto.Dependente;
import br.org.flem.fw.persistencia.dto.Funcionario;
import br.org.flem.fw.persistencia.dto.planserv.PlanservDTO;
import br.org.flem.fw.service.IFuncionario;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.util.Calendario;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.dto.planserv.Beneficiario;
import br.org.flem.sac.dto.planserv.SituacaoFuncional;
import br.org.flem.sac.dto.planserv.validacaoFolha.Associado;
import br.org.flem.sac.dto.planserv.validacaoFolha.GrupoFamiliar;
import br.org.flem.sac.dto.planserv.validacaoFolha.Pagamento;
import br.org.flem.sac.dto.planserv.validacaoFolha.TipoPagamentoPlanserv;
import br.org.flem.sac.dto.planserv.validacaoFolha.ValidacaoFolha;
import br.org.flem.sac.exceptions.FuncionarioSemCadastroNoPlanservException;
import br.org.flem.sac.negocio.Titular;
import br.org.flem.sac.planserv.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.GenericValidator;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author mjpereira
 */
public class PlanservBO {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendario calendario = new Calendario();

    public String gerarArquivoPlanserv(Set<Beneficiario> set, String caminho) throws ParseException, IOException {
        List lista = new ArrayList();
        Date data = new Date();

        ArquivoHeader ah = new ArquivoHeader();
        ah.setDataGravacao(data);

        OrgaoHeader oh = new OrgaoHeader();
        ArquivoTrailer at = new ArquivoTrailer();
        OrgaoTrailer ot = new OrgaoTrailer();

        lista.add(ah);
        lista.add(oh);
        int count = 3;
        int dependente = 0;
        int titular = 0;
        List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>(set);
        Collections.sort(beneficiarios, new Comparator<Beneficiario>() {
            @Override
            public int compare(Beneficiario o1, Beneficiario o2) {
                return o1.getRdp() - o2.getRdp();
            }
        });

        for (Beneficiario b : beneficiarios) {
            Detalhe d = new Detalhe();
            d.setTipoOperacao(b.getCdOperacao());
            d.setMatricula(Integer.valueOf(b.getMatricula()));
            d.setRdp(b.getRdp());
            d.setNome(b.getNome());
            d.setNascimento(sdf.parse(b.getDataNascimento()));
            d.setSexo(b.getSexo());
            d.setDataOperacao(sdf.parse(b.getDataOperacao()));
            d.setGrauDependencia(b.getCdParentesco());
            d.setCodigoPlano(Integer.valueOf(b.getCdPlano()));
            d.setSituacaoFuncional(obterSituacaoFuncionalPorDepartamento(b.getFuncionario().getDepartamento()));
            d.setAdmissao(b.getFuncionario().getAdmissao());
            d.setCpf(b.getFuncionario().getCpf());
            d.setLogradouro(b.getFuncionario().getEndereco().getDescricao());
            d.setNumero(b.getFuncionario().getEndereco().getNumero());
            d.setComplemento(b.getFuncionario().getEndereco().getComplemento());
            d.setBairro(b.getFuncionario().getEndereco().getBairro());
            d.setMunicipio(b.getFuncionario().getEndereco().getCidade());
            d.setCep(b.getFuncionario().getEndereco().getCep());
            d.setTelefone(b.getFuncionario().getTelefone());
            d.setNsr(count++);
            if (GenericValidator.isDate(b.getDataBeneficio(), "dd/MM/yyyy", false)) {
                d.setBeneficio(sdf.parse(b.getDataBeneficio()));
            }
            if (GenericValidator.isDate(b.getDataCasamento(), "dd/MM/yyyy", false)) {
                d.setCasamento(sdf.parse(b.getDataCasamento()));
            }
            lista.add(d);
            if (b.isTitular()) {
                titular++;
            } else {
                dependente++;
            }
        }
        if (count == 3) {
            count++;
        }

        ot.setNsr(count);
        ot.setTotalDependentes(dependente);
        ot.setTotalTitulares(titular);
        lista.add(ot);
        count++;
        at.setTotalDependentes(dependente);
        at.setTotalTitulares(titular);
        at.setNsr(count);
        lista.add(at);
        StringBuilder nome = new StringBuilder();
        nome.append("p");
        nome.append(new SimpleDateFormat("yyMMddhhmm").format(new Date()));
        nome.append(".fle");
        File f = new File(caminho + nome.toString());
        new Arquivo().geraArquivo(lista, f);
        return nome.toString();
    }
    
    public SituacaoFuncional obterSituacaoFuncionalPorDepartamento(Departamento d){
        return d.getCodigoDominio().equals(Integer.parseInt(PropertiesUtil.getProperty(("departamentoPrimeiroEmprego")))) ? SituacaoFuncional.ATIVO_SEM_DEPENDENTE : SituacaoFuncional.FUNCIONARIO_EM_ATIVIDADE;
    }

    public Double[] obterDescontoPlanserv(Double salario) {
        Double[] salarios = new Double[69];
        Double[][] descontos = new Double[69][3];
        int i = 0;
        int posicaoDesconto = 0;
        
        /**
        salarios[0] = 350.00; descontos[0][0] = 26.00; descontos[0][1] = 13.00; descontos[0][2] = 5.72;
        salarios[1] = 450.00; descontos[1][0] = 36.00; descontos[1][1] = 18.00; descontos[1][2] = 7.92;
        salarios[2] = 550.00; descontos[2][0] = 46.00; descontos[2][1] = 23.00; descontos[2][2] = 10.12;
        salarios[3] = 650.00; descontos[3][0] = 50.70; descontos[3][1] = 25.35; descontos[3][2] = 11.15;
        salarios[4] = 750.00; descontos[4][0] = 59.80; descontos[4][1] = 29.90; descontos[4][2] = 13.16;
        salarios[5] = 850.00; descontos[5][0] = 68.90; descontos[5][1] = 34.45; descontos[5][2] = 15.16;
        salarios[6] = 950.00; descontos[6][0] = 78.00; descontos[6][1] = 39.00; descontos[6][2] = 17.16;
        salarios[7] = 1050.00; descontos[7][0] = 87.10; descontos[7][1] = 43.55; descontos[7][2] = 19.16;
        salarios[8] = 1150.00; descontos[8][0] = 96.20; descontos[8][1] = 48.10; descontos[8][2] = 21.16;
        salarios[9] = 1250.00; descontos[9][0] = 105.30; descontos[9][1] = 52.65; descontos[9][2] = 23.17;
        salarios[10] = 1350.00; descontos[10][0] = 114.40; descontos[10][1] = 57.20; descontos[10][2] = 25.17;
        salarios[11] = 1450.00; descontos[11][0] = 123.50; descontos[11][1] = 61.75; descontos[11][2] = 27.17;
        salarios[12] = 1550.00; descontos[12][0] = 132.60; descontos[12][1] = 66.30; descontos[12][2] = 29.17;
        salarios[13] = 1650.00; descontos[13][0] = 141.70; descontos[13][1] = 70.85; descontos[13][2] = 31.17;
        salarios[14] = 1750.00; descontos[14][0] = 150.80; descontos[14][1] = 75.40; descontos[14][2] = 33.18;
        salarios[15] = 1850.00; descontos[15][0] = 159.90; descontos[15][1] = 79.95; descontos[15][2] = 35.18;
        salarios[16] = 1950.00; descontos[16][0] = 169.00; descontos[16][1] = 84.50; descontos[16][2] = 37.18;
        salarios[17] = 2050.00; descontos[17][0] = 178.10; descontos[17][1] = 89.05; descontos[17][2] = 39.18;
        salarios[18] = 2150.00; descontos[18][0] = 187.20; descontos[18][1] = 93.60; descontos[18][2] = 41.18;
        salarios[19] = 2250.00; descontos[19][0] = 196.30; descontos[19][1] = 98.15; descontos[19][2] = 43.19;
        salarios[20] = 2350.00; descontos[20][0] = 205.40; descontos[20][1] = 102.70; descontos[20][2] = 45.19;
        salarios[21] = 2450.00; descontos[21][0] = 214.50; descontos[21][1] = 107.25; descontos[21][2] = 47.19;
        salarios[22] = 2550.00; descontos[22][0] = 223.60; descontos[22][1] = 111.80; descontos[22][2] = 49.19;
        salarios[23] = 2650.00; descontos[23][0] = 232.70; descontos[23][1] = 116.35; descontos[23][2] = 51.19;
        salarios[24] = 2750.00; descontos[24][0] = 241.80; descontos[24][1] = 120.90; descontos[24][2] = 53.20;
        salarios[25] = 2850.00; descontos[25][0] = 250.90; descontos[25][1] = 125.45; descontos[25][2] = 55.20;
        salarios[26] = 2950.00; descontos[26][0] = 260.00; descontos[26][1] = 130.00; descontos[26][2] = 57.20;
        salarios[27] = 3050.00; descontos[27][0] = 269.10; descontos[27][1] = 134.55; descontos[27][2] = 59.20;
        salarios[28] = 3150.00; descontos[28][0] = 278.20; descontos[28][1] = 139.10; descontos[28][2] = 61.20;
        salarios[29] = 3250.00; descontos[29][0] = 287.30; descontos[29][1] = 143.65; descontos[29][2] = 63.21;
        salarios[30] = 3750.00; descontos[30][0] = 296.40; descontos[30][1] = 148.20; descontos[30][2] = 65.21;
        salarios[31] = 4250.00; descontos[31][0] = 305.50; descontos[31][1] = 152.75; descontos[31][2] = 67.21;
        salarios[32] = 4750.00; descontos[32][0] = 314.60; descontos[32][1] = 157.30; descontos[32][2] = 69.21;
        salarios[33] = 5250.00; descontos[33][0] = 323.70; descontos[33][1] = 161.85; descontos[33][2] = 71.21;
        salarios[34] = 5750.00; descontos[34][0] = 332.80; descontos[34][1] = 166.40; descontos[34][2] = 73.22;
        salarios[35] = 6250.00; descontos[35][0] = 341.90; descontos[35][1] = 170.95; descontos[35][2] = 75.22;
        salarios[36] = 6750.00; descontos[36][0] = 351.00; descontos[36][1] = 175.50; descontos[36][2] = 77.22;
        salarios[37] = 7250.00; descontos[37][0] = 360.10; descontos[37][1] = 180.05; descontos[37][2] = 79.22;
        salarios[38] = 7750.00; descontos[38][0] = 369.20; descontos[38][1] = 184.60; descontos[38][2] = 81.22;
        salarios[39] = 8250.00; descontos[39][0] = 378.30; descontos[39][1] = 189.15; descontos[39][2] = 83.23;
        salarios[40] = 8750.00; descontos[40][0] = 387.40; descontos[40][1] = 193.70; descontos[40][2] = 85.23;
        salarios[41] = 9250.00; descontos[41][0] = 396.50; descontos[41][1] = 198.25; descontos[41][2] = 87.23;
        salarios[42] = 9750.00; descontos[42][0] = 405.60; descontos[42][1] = 202.80; descontos[42][2] = 89.23;
        salarios[43] = 10250.00; descontos[43][0] = 414.70; descontos[43][1] = 207.35; descontos[43][2] = 91.23;
        salarios[44] = 10750.00; descontos[44][0] = 423.80; descontos[44][1] = 211.90; descontos[44][2] = 93.24;
        salarios[45] = 11250.00; descontos[45][0] = 432.90; descontos[45][1] = 216.45; descontos[45][2] = 95.24;
        salarios[46] = 11750.00; descontos[46][0] = 442.00; descontos[46][1] = 221.00; descontos[46][2] = 97.24;
        salarios[47] = 12250.00; descontos[47][0] = 451.10; descontos[47][1] = 225.55; descontos[47][2] = 99.24;
        salarios[48] = 12750.00; descontos[48][0] = 460.20; descontos[48][1] = 230.10; descontos[48][2] = 101.24;
        salarios[49] = 13250.00; descontos[49][0] = 469.30; descontos[49][1] = 234.65; descontos[49][2] = 103.25;
        salarios[50] = 13750.00; descontos[50][0] = 478.40; descontos[50][1] = 239.20; descontos[50][2] = 105.25;
        salarios[51] = 14250.00; descontos[51][0] = 487.50; descontos[51][1] = 243.75; descontos[51][2] = 107.25;
        salarios[52] = 14750.00; descontos[52][0] = 496.60; descontos[52][1] = 248.30; descontos[52][2] = 109.25;
        salarios[53] = 15250.00; descontos[53][0] = 505.70; descontos[53][1] = 252.85; descontos[53][2] = 111.25;
        salarios[54] = 15750.00; descontos[54][0] = 514.80; descontos[54][1] = 257.40; descontos[54][2] = 113.26;
        salarios[55] = 16250.00; descontos[55][0] = 523.90; descontos[55][1] = 261.95; descontos[55][2] = 115.26;
        salarios[56] = 16750.00; descontos[56][0] = 533.00; descontos[56][1] = 266.50; descontos[56][2] = 117.26;
        salarios[57] = 17250.00; descontos[57][0] = 542.10; descontos[57][1] = 271.05; descontos[57][2] = 119.26;
        salarios[58] = 17750.00; descontos[58][0] = 551.20; descontos[58][1] = 275.60; descontos[58][2] = 121.26;
        salarios[59] = 18250.00; descontos[59][0] = 560.30; descontos[59][1] = 280.15; descontos[59][2] = 123.27;
        salarios[60] = 18750.00; descontos[60][0] = 569.40; descontos[60][1] = 284.70; descontos[60][2] = 125.27;
        salarios[61] = 19250.00; descontos[61][0] = 578.50; descontos[61][1] = 289.25; descontos[61][2] = 127.27;
        salarios[62] = 19750.00; descontos[62][0] = 587.60; descontos[62][1] = 293.80; descontos[62][2] = 129.27;
        salarios[63] = 20250.00; descontos[63][0] = 596.70; descontos[63][1] = 298.35; descontos[63][2] = 131.27;
        salarios[64] = 20750.00; descontos[64][0] = 605.80; descontos[64][1] = 302.90; descontos[64][2] = 133.28;
        salarios[65] = 21250.00; descontos[65][0] = 614.90; descontos[65][1] = 307.45; descontos[65][2] = 135.28;
        salarios[66] = 21750.00; descontos[66][0] = 624.00; descontos[66][1] = 312.00; descontos[66][2] = 137.28;
        salarios[67] = 22250.00; descontos[67][0] = 633.10; descontos[67][1] = 316.55; descontos[67][2] = 139.28;
        salarios[68] = 99999.00; descontos[68][0] = 642.20; descontos[68][1] = 321.10; descontos[68][2] = 141.28;*/
        
        
        salarios[0] = 350.00; descontos[0][0] = 27.04; descontos[0][1] = 13.52; descontos[0][2] = 5.95;
        salarios[1] = 450.00; descontos[1][0] = 37.44; descontos[1][1] = 18.72; descontos[1][2] = 8.24;
        salarios[2] = 550.00; descontos[2][0] = 47.84; descontos[2][1] = 23.92; descontos[2][2] = 10.52;
        salarios[3] = 650.00; descontos[3][0] = 52.73; descontos[3][1] = 26.36; descontos[3][2] = 11.60;
        salarios[4] = 750.00; descontos[4][0] = 62.19; descontos[4][1] = 31.10; descontos[4][2] = 13.69;
        salarios[5] = 850.00; descontos[5][0] = 71.66; descontos[5][1] = 35.83; descontos[5][2] = 15.77;
        salarios[6] = 950.00; descontos[6][0] = 81.12; descontos[6][1] = 40.56; descontos[6][2] = 17.85;
        salarios[7] = 1050.00; descontos[7][0] = 90.58; descontos[7][1] = 45.29; descontos[7][2] = 19.93;
        salarios[8] = 1150.00; descontos[8][0] = 100.05; descontos[8][1] = 50.02; descontos[8][2] = 22.01;
        salarios[9] = 1250.00; descontos[9][0] = 109.51; descontos[9][1] = 54.76; descontos[9][2] = 24.10;
        salarios[10] = 1350.00; descontos[10][0] = 118.98; descontos[10][1] = 59.49; descontos[10][2] = 26.18;
        salarios[11] = 1450.00; descontos[11][0] = 128.44; descontos[11][1] = 64.22; descontos[11][2] = 28.26;
        salarios[12] = 1550.00; descontos[12][0] = 137.90; descontos[12][1] = 68.95; descontos[12][2] = 30.34;
        salarios[13] = 1650.00; descontos[13][0] = 147.37; descontos[13][1] = 73.68; descontos[13][2] = 32.42;
        salarios[14] = 1750.00; descontos[14][0] = 156.83; descontos[14][1] = 78.42; descontos[14][2] = 34.51;
        salarios[15] = 1850.00; descontos[15][0] = 166.30; descontos[15][1] = 83.15; descontos[15][2] = 36.59;
        salarios[16] = 1950.00; descontos[16][0] = 175.76; descontos[16][1] = 87.88; descontos[16][2] = 38.67;
        salarios[17] = 2050.00; descontos[17][0] = 185.22; descontos[17][1] = 92.61; descontos[17][2] = 40.75;
        salarios[18] = 2150.00; descontos[18][0] = 194.69; descontos[18][1] = 97.34; descontos[18][2] = 42.83;
        salarios[19] = 2250.00; descontos[19][0] = 204.15; descontos[19][1] = 102.08; descontos[19][2] = 44.92;
        salarios[20] = 2350.00; descontos[20][0] = 213.62; descontos[20][1] = 106.81; descontos[20][2] = 47.00;
        salarios[21] = 2450.00; descontos[21][0] = 223.08; descontos[21][1] = 111.54; descontos[21][2] = 49.08;
        salarios[22] = 2550.00; descontos[22][0] = 232.54; descontos[22][1] = 116.27; descontos[22][2] = 51.16;
        salarios[23] = 2650.00; descontos[23][0] = 242.01; descontos[23][1] = 121.00; descontos[23][2] = 53.24;
        salarios[24] = 2750.00; descontos[24][0] = 251.47; descontos[24][1] = 125.74; descontos[24][2] = 55.33;
        salarios[25] = 2850.00; descontos[25][0] = 260.94; descontos[25][1] = 130.47; descontos[25][2] = 57.41;
        salarios[26] = 2950.00; descontos[26][0] = 270.40; descontos[26][1] = 135.20; descontos[26][2] = 59.49;
        salarios[27] = 3050.00; descontos[27][0] = 279.86; descontos[27][1] = 139.93; descontos[27][2] = 61.57;
        salarios[28] = 3150.00; descontos[28][0] = 289.33; descontos[28][1] = 144.66; descontos[28][2] = 63.65;
        salarios[29] = 3250.00; descontos[29][0] = 298.79; descontos[29][1] = 149.40; descontos[29][2] = 65.74;
        salarios[30] = 3750.00; descontos[30][0] = 308.26; descontos[30][1] = 154.13; descontos[30][2] = 67.82;
        salarios[31] = 4250.00; descontos[31][0] = 317.72; descontos[31][1] = 158.86; descontos[31][2] = 69.90;
        salarios[32] = 4750.00; descontos[32][0] = 327.18; descontos[32][1] = 163.59; descontos[32][2] = 71.98;
        salarios[33] = 5250.00; descontos[33][0] = 336.65; descontos[33][1] = 168.32; descontos[33][2] = 74.06;
        salarios[34] = 5750.00; descontos[34][0] = 346.11; descontos[34][1] = 173.06; descontos[34][2] = 76.15;
        salarios[35] = 6250.00; descontos[35][0] = 355.58; descontos[35][1] = 177.79; descontos[35][2] = 78.23;
        salarios[36] = 6750.00; descontos[36][0] = 365.04; descontos[36][1] = 182.52; descontos[36][2] = 80.31;
        salarios[37] = 7250.00; descontos[37][0] = 374.50; descontos[37][1] = 187.25; descontos[37][2] = 82.39;
        salarios[38] = 7750.00; descontos[38][0] = 383.97; descontos[38][1] = 191.98; descontos[38][2] = 84.47;
        salarios[39] = 8250.00; descontos[39][0] = 393.43; descontos[39][1] = 196.72; descontos[39][2] = 86.56;
        salarios[40] = 8750.00; descontos[40][0] = 402.90; descontos[40][1] = 201.45; descontos[40][2] = 88.64;
        salarios[41] = 9250.00; descontos[41][0] = 412.36; descontos[41][1] = 206.18; descontos[41][2] = 90.72;
        salarios[42] = 9750.00; descontos[42][0] = 421.82; descontos[42][1] = 210.91; descontos[42][2] = 92.80;
        salarios[43] = 10250.00; descontos[43][0] = 431.29; descontos[43][1] = 215.64; descontos[43][2] = 94.88;
        salarios[44] = 10750.00; descontos[44][0] = 440.75; descontos[44][1] = 220.38; descontos[44][2] = 96.97;
        salarios[45] = 11250.00; descontos[45][0] = 450.22; descontos[45][1] = 225.11; descontos[45][2] = 99.05;
        salarios[46] = 11750.00; descontos[46][0] = 459.68; descontos[46][1] = 229.84; descontos[46][2] = 101.13;
        salarios[47] = 12250.00; descontos[47][0] = 469.14; descontos[47][1] = 234.57; descontos[47][2] = 103.21;
        salarios[48] = 12750.00; descontos[48][0] = 478.61; descontos[48][1] = 239.30; descontos[48][2] = 105.29;
        salarios[49] = 13250.00; descontos[49][0] = 488.07; descontos[49][1] = 244.04; descontos[49][2] = 107.38;
        salarios[50] = 13750.00; descontos[50][0] = 497.54; descontos[50][1] = 248.77; descontos[50][2] = 109.46;
        salarios[51] = 14250.00; descontos[51][0] = 507.00; descontos[51][1] = 253.50; descontos[51][2] = 111.54;
        salarios[52] = 14750.00; descontos[52][0] = 516.46; descontos[52][1] = 258.23; descontos[52][2] = 113.62;
        salarios[53] = 15250.00; descontos[53][0] = 525.93; descontos[53][1] = 262.96; descontos[53][2] = 115.70;
        salarios[54] = 15750.00; descontos[54][0] = 535.39; descontos[54][1] = 267.70; descontos[54][2] = 117.79;
        salarios[55] = 16250.00; descontos[55][0] = 544.86; descontos[55][1] = 272.43; descontos[55][2] = 119.87;
        salarios[56] = 16750.00; descontos[56][0] = 554.32; descontos[56][1] = 277.16; descontos[56][2] = 121.95;
        salarios[57] = 17250.00; descontos[57][0] = 563.78; descontos[57][1] = 281.89; descontos[57][2] = 124.03;
        salarios[58] = 17750.00; descontos[58][0] = 573.25; descontos[58][1] = 286.62; descontos[58][2] = 126.11;
        salarios[59] = 18250.00; descontos[59][0] = 582.71; descontos[59][1] = 291.36; descontos[59][2] = 128.20;
        salarios[60] = 18750.00; descontos[60][0] = 592.18; descontos[60][1] = 296.09; descontos[60][2] = 130.28;
        salarios[61] = 19250.00; descontos[61][0] = 601.64; descontos[61][1] = 300.82; descontos[61][2] = 132.36;
        salarios[62] = 19750.00; descontos[62][0] = 611.10; descontos[62][1] = 305.55; descontos[62][2] = 134.44;
        salarios[63] = 20250.00; descontos[63][0] = 620.57; descontos[63][1] = 310.28; descontos[63][2] = 136.52;
        salarios[64] = 20750.00; descontos[64][0] = 630.03; descontos[64][1] = 315.02; descontos[64][2] = 138.61;
        salarios[65] = 21250.00; descontos[65][0] = 639.50; descontos[65][1] = 319.75; descontos[65][2] = 140.69;
        salarios[66] = 21750.00; descontos[66][0] = 648.96; descontos[66][1] = 324.48; descontos[66][2] = 142.77;
        salarios[67] = 22250.00; descontos[67][0] = 658.42; descontos[67][1] = 329.21; descontos[67][2] = 144.85;
        salarios[68] = 99999.00; descontos[68][0] = 667.89; descontos[68][1] = 333.94; descontos[68][2] = 146.93;

        if (salario > salarios[i]) {
            for (i = 0; i < 69; i++) {
                if (salario > salarios[i] && salario <= salarios[i + 1]) {
                    posicaoDesconto = i + 1;
                    break;
                }
            }
        }
        return descontos[posicaoDesconto];
    }

    public List<Layout> geraArquivoMensal(String mes, String ano,Boolean desprezarErros) throws AplicacaoException, ParseException, FuncionarioSemCadastroNoPlanservException {
        desprezarErros = true;
        List<Layout> layout = new ArrayList<Layout>();
        ArquivoHeaderMensal arquivoHeaderMensal = new ArquivoHeaderMensal();
        ClienteHeaderMensal clienteHeaderMensal = new ClienteHeaderMensal();
        TrailerClienteMensal trailerClienteMensal = new TrailerClienteMensal();
        TrailerArquivoMensal trailerArquivoMensal = new TrailerArquivoMensal();
        int numeroRegistro = 1;
        RHServico rh = new RHServico();
        
        
        Calendar dataFolha = Calendar.getInstance();

        Map<Integer, Collection<PlanservDTO>> mapVerbas = new HashMap<Integer, Collection<PlanservDTO>>();

        dataFolha.set(Calendar.MONTH, Integer.parseInt(mes));
        dataFolha.set(Calendar.YEAR, Integer.parseInt(ano));
        //ultimo dia do m�s anterior
        dataFolha.set(Calendar.DAY_OF_MONTH, dataFolha.getActualMaximum(Calendar.DAY_OF_MONTH));

        mapVerbas = rh.obterValoresPlanservDescontados(mes, ano);

        Collection<Integer> matriculaInt = mapVerbas.keySet(); // Vem do CHRBI, com base nos descontos em folh
        
        Collection<Titular> matriculasAtivasERPSERVICO = new TitularBO().obterTitularesAtivo(); // Vem do ERPSERVICO, da tabela titulares
        Collection<Integer> matriculaIntERPSERVICO = new ArrayList<Integer>();
        String matriculas = this.matriculasParaString(matriculaInt);
        Map<Integer,Double> salarios = rh.salariosNaData(matriculas,dataFolha.getTime());
        //List<IFuncionario> funcionarioList = rh.obterTodosPorPlanservMatriculasFuncionario(matriculas);//Deixado de utilizar por muita gente n�o estar sendo sinalizada no sistema que tem planserv
        List<Funcionario> funcionarioList = rh.obterFuncionarios();
        Map<Integer, List<Dependente>> depMap = rh.obterMapDependentesPlanservPorMatriculas(matriculas); // s� vem dependente ativo do CHRBI

        String matriculasSemCadastro = "";
        String matriculasAMais = "";

        for (Titular t : matriculasAtivasERPSERVICO) {
            if (t.getCod_Mat_Titular() > 0) {
                matriculaIntERPSERVICO.add(t.getCod_Mat_Titular());
            }
        }

        for (Integer matricula : matriculaInt) {
            if (!matriculaIntERPSERVICO.contains(matricula)) {
                matriculasSemCadastro += String.valueOf(matricula) + ",";
            }
        }

        /*if (matriculaInt.size() > matriculaIntERPSERVICO.size()) {
            matriculasSemCadastro = matriculasSemCadastro.substring(0, matriculasSemCadastro.length() - 1);
            if(!desprezarErros){
                throw new FuncionarioSemCadastroNoPlanservException("O total de Titulares Ativos do ERPSERVICO n�o bate com o total do CONSIST HR com desconto em folha para o Planserv. Para regularizar, gere o arquivo de Inclus�o de Plano das matriculas "+matriculasSemCadastro+" no ERPSERVICO e, caso ainda n�o tenha sido enviado ao Planserv, realizar o envio!");
            }
        }

        Funcionario funcionario;
        if (matriculaInt.size() < matriculaIntERPSERVICO.size()) {
            for (Integer matricula : matriculaIntERPSERVICO) {
                if (!matriculaInt.contains(matricula)) {
                    //S� deve considerar incorreto se o usuario n�o tiver sido demitido no m�s que est� sendo gerado.
                    funcionario = (Funcionario)rh.obterFuncionarioPorMatricula(matricula);
                    if (funcionario.getRescisao() == null) {
                        matriculasAMais += String.valueOf(matricula) + ",";
                    } else {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(funcionario.getRescisao());
                        if (dta.get(Calendar.MONTH) != cal.get(Calendar.MONTH)) {
                            matriculasAMais += String.valueOf(matricula) + ",";
                        }
                    }
                }
            }
            if (matriculasAMais.length() > 0) {
                matriculasAMais = matriculasAMais.substring(0, (matriculasAMais.length() - 1));
                if(!desprezarErros){
                    throw new FuncionarioSemCadastroNoPlanservException("O total de Titulares Ativos do ERPSERVICO n�o bate com o total do CONSIST HR com desconto em folha para o Planserv. Para regularizar, gere o arquivo de Exclus�o de Plano das matriculas " + matriculasAMais + " no ERPSERVICO e, caso ainda n�o tenha sido enviado ao Planserv, realizar o envio!");
                }
            }
        }*/

        /**
         * ********************************************************************
         * Cria o Header do Arquivo Mensal
         * *******************************************************************
         */
        arquivoHeaderMensal.setNumeroRegistro(numeroRegistro);
        arquivoHeaderMensal.setDataGravacao(dataFolha.getTime());
        numeroRegistro++;
        layout.add(arquivoHeaderMensal);

        /**
         * ********************************************************************
         * Cria o Header do Cliente
         * *******************************************************************
         */
        clienteHeaderMensal.setNumeroRegistro(numeroRegistro);
        numeroRegistro++;
        layout.add(clienteHeaderMensal);

        /**
         * ********************************************************************
         * Cria os Detalhes dos Funcion�rios
         * *******************************************************************
         */
        StringBuilder erros = new StringBuilder();
        for (IFuncionario iFuncionario : funcionarioList) {
            if (mapVerbas.containsKey(iFuncionario.getMatricula())) {
                Collection<PlanservDTO> descontosFuncionario = mapVerbas.get(iFuncionario.getMatricula());

                try {
                    layout.add(geraDetalheMensal(iFuncionario, depMap, numeroRegistro++, descontosFuncionario, desprezarErros, salarios, dataFolha.getTime()));
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                    erros.append(e.getMessage()).append(" ");
                }
            }
        }
        /** Teste
         * 
        if(erros.length() > 0){
            throw new AplicacaoException(erros.toString());
        }
        * 
        * */
        /*if(erros.length() > 0){
            if(!desprezarErros){
                throw new AplicacaoException("Calculo de desconto divergente do valor da folha nos funcionarios: "+erros.toString());
            }
        }*/



        /**
         * ********************************************************************
         * Cria o Trailer do Cliente
         * *******************************************************************
         */
        trailerClienteMensal.setNumeroRegistro(numeroRegistro);
        numeroRegistro++;
        layout.add(trailerClienteMensal);

        /**
         * ********************************************************************
         * Cria o Trailer do Arquivo
         * *******************************************************************
         */
        trailerArquivoMensal.setNumeroRegistro(numeroRegistro);
        trailerArquivoMensal.setTotalTitulares(mapVerbas.size());
        layout.add(trailerArquivoMensal);
        return layout;
    }

    public DetalheMensal geraDetalheMensal(IFuncionario iFuncionario, Map<Integer, List<Dependente>> depMap, int numeroRegistro, Collection<PlanservDTO> descontosPlanserv, Boolean desprezarErros,Map<Integer,Double> salarios,Date dataFolha) throws AplicacaoException {
        float idade = 0;
        int totalDep = 0;
        int totalAgregadoMenor = 0;
        int totalAgregadoMaior = 0;
        Double totalAgregadoMenorSomatorio = 0.0;
        Double totalAgregadoMaiorSomatorio = 0.0;

        Double baseCalculo = 0.0;//sal�rio do funcion�rio
        Double valorDescontoDependente = 0.0;//com base no sal�rio

        Double valorDescontoAgregadoMenor25 = Double.parseDouble(PropertiesUtil.getProperty("valorDescontoAgregadoMenor25"));

        Double valorTitular = 0.0;
        Double valorEspecial = 0.0;//pega do chrbi

        //campos novos: atualiza��o feita em 20/09/2011
        //INI
        Double valorDependenteRetroativo = 0.0;
        Double valorDescontoAgregadoMenorRetroativo = 0.0;
        Double valorDescontoAgregadoMaiorRetroativo = 0.0;
        Double valorTitularRetroativo = 0.0;
        Double valorEspecialRetroativo = 0.0;
        Double valorConjuge = 0.0;
        Double valorConjugeRetroativo = 0.0;
        Double valorCoParticipacao = 0.0;
        //FIM
        
        //NOVOS CAMPOS DO NOVO LAYOUT. IMPLEMENTADO EM 12/05/2021
        //IN�CIO
        Date dataTeste = null;
        Date dataAdesao = null;
        int difTempoDeAdesao = 0;
        Double valorTitularParcelaRisco = 0.0;
        Double valorConjugeParcelaRisco = 0.0;
        Double valorDependenteParcelaRisco = 0.0;
        Double valorAgregadoMenorParcelaRisco = 0.0;
        Double valorAgregadoMaiorParcelaRisco = 0.0;
        
        /**
         * Tabela de valores da Parcela de Risco em fun��o da idade do titular,
         * companheiro, c�njuge, dependente, agregado maior e agregado menor,
         * conforme parag.1�, art. 10, linha A:for i
         * 
         * Crit�rio base: Funcion�rio deve ter 5 anos ou mais de diferen�a entre
         *  a data de ades�o ao plano de sa�de e sua admiss�o ao emprego.
         *  PARCELA_RISCO_COTA1 <25 anos: R$82.97
         *  PARCELA_RISCO_COTA2 >=25 && <30: R$133.77
         *  PARCELA_RISCO_COTA3 >=30 && <40: R$169.42
         *  PARCELA_RISCO_COTA4 >=40 && <50: R$192.63
         *  PARCELA_RISCO_COTA5 >=50 && <60: R$265.46
         *  PARCELA_RISCO_COTA6 >=60: R$544.00
         * 
         */
        
        /**
        Double PARCELA_RISCO_COTA1 = 82.97;
        Double PARCELA_RISCO_COTA2 = 133.77;
        Double PARCELA_RISCO_COTA3 = 169.42;
        Double PARCELA_RISCO_COTA4 = 192.63;
        Double PARCELA_RISCO_COTA5 = 265.46;
        Double PARCELA_RISCO_COTA6 = 544.0;
        */
        
        Double PARCELA_RISCO_COTA1 = 86.29;
        Double PARCELA_RISCO_COTA2 = 139.12;
        Double PARCELA_RISCO_COTA3 = 176.20;
        Double PARCELA_RISCO_COTA4 = 200.34;
        Double PARCELA_RISCO_COTA5 = 276.08;
        Double PARCELA_RISCO_COTA6 = 565.76;
        
        //FIM
        
        Double[] descontos = new Double[3];
        int relacaoDependencia = 0;


        //loop dos Funcion�rios com Planserv
        List<Dependente> dependentes = null;
        Integer conjugue = 0;
        DetalheMensal detalheMensal = new DetalheMensal();

        detalheMensal.setNumeroRegistro(numeroRegistro);
        numeroRegistro++;

        if (depMap.get(iFuncionario.getMatricula()) != null) {
            dependentes = depMap.get(iFuncionario.getMatricula());
        } else {
            dependentes = new ArrayList<Dependente>();
        }
        totalDep = 0;
        totalAgregadoMenor = 0;
        totalAgregadoMaior = 0;

        baseCalculo = salarios.get(iFuncionario.getMatricula());//Salarios agora contam com o historico do mesmo na data
        
        System.out.println("Nome: " + iFuncionario.getNome());
        System.out.println("Situa��o: " + iFuncionario.getSituacao().toString());
        
        //pega o desconto do chrbi com base no sal�rio(baseCalculo)
        descontos = obterDescontoPlanserv(baseCalculo);

        valorTitular = descontos[0];
        
        
        valorDescontoDependente = descontos[2];

        

        for(PlanservDTO verba : descontosPlanserv){
            if(verba.getVerbasPlanservEnum().equals(VerbasPlanservEnum.ESPECIAL)){
                valorEspecial = Double.parseDouble(PropertiesUtil.getProperty("valorEspecial"));
                break;
            }
        }
        
        /**
        //Se o Funcion�rio tiver Dependentes, calcula quantos s�o DEP ou AGR maior e menor
        for (Dependente dependente : dependentes) {
            if (dependente.isConjuge()) {
                valorConjuge = descontos[1];
                conjugue = 1;
            } else {
                if(dependente.getNascimento() == null){
                    throw  new AplicacaoException("Data de nascimento n�o preenchida do dependente "+dependente.getNome()+" do titular "+dependente.getMatricula());
                }
                idade = dependente.getNascimento() != null ? Data.anoDiffDecimal(dependente.getNascimento(), dataFolha) : 0;
                if (dependente.isAgregado() || idade > 18) {
                    if (idade > 24) {
                        totalAgregadoMaior++;
                        totalAgregadoMaiorSomatorio += valorTitular; ///Nova regra, maior ou igual a 25 anos custeio integral do valor do titular
                    } else{
                        totalAgregadoMenor++;
                        totalAgregadoMenorSomatorio += valorDescontoAgregadoMenor25;
                    }
                }else{
                    totalDep++;
                }
            }
        }
        * */
        
        //ALGORITMO DO C�LCULO DA PARCELA DE RISCO - IMPLEMENTADO EM 12/05/2021
        //IN�CIO
        
        //Verifica se existe ades�o ao plano
        System.out.println("Nome do funcion�rio: " + iFuncionario.getNome());
        System.out.println("Data de Admiss�o: " + iFuncionario.getAdmissao());
        System.out.println("Data de Ades�o ao Planserv: " + iFuncionario.getPlanservAdesao());
        try 
        {
            dataTeste = new SimpleDateFormat("dd-MM-YYYY").parse("01-01-0001");
        } 
        catch (ParseException ex) 
        {
            System.out.println("Erro no parsing de data");
        }
        try 
        {
            dataAdesao = iFuncionario.getPlanservAdesao();
            if (dataAdesao.compareTo(dataTeste) == 0)
            {
                System.out.println("Funcion�rio SEM Ades�o a Plano");
            }
            else 
            {
                //Crit�rio para pagamento da Parcela de Risco: Funcion�rio deve ter 5 anos ou mais entre a data de admiss�o e a data de ades�o ao plano

                String strDataAdmissao = new SimpleDateFormat("dd-MM-YYYY").format(iFuncionario.getAdmissao());

                DateTime dateTimeAdmissao = new DateTime(Integer.parseInt(strDataAdmissao.substring(6, 10)),
                                        Integer.parseInt(strDataAdmissao.substring(3, 5)), 
                                        Integer.parseInt(strDataAdmissao.substring(0, 2)), 0, 0, 0);
                String strDataAdesao = new SimpleDateFormat("dd-MM-YYYY").format(dataAdesao);

                DateTime dateTimeAdesao = new DateTime(Integer.parseInt(strDataAdesao.substring(6, 10)),
                                        Integer.parseInt(strDataAdesao.substring(3, 5)), 
                                        Integer.parseInt(strDataAdesao.substring(0, 2)), 0, 0, 0);

                Period period = new Period(dateTimeAdmissao, dateTimeAdesao);

                difTempoDeAdesao = (period.getYears() * 12) + period.getMonths();
                System.out.println("Diferencial do Tempo de Ades�o: " + difTempoDeAdesao + " meses");
            }
        } 
        catch (java.lang.NullPointerException e) 
        {
            System.out.println("Funcion�rio com Data de Ades�o igual a NULL");
            dataAdesao = dataTeste;
        }
        
        
        
        //C�lculo da Parcela de Risco do Titular
        idade = Data.anoDiffDecimal(iFuncionario.getNascimento(), dataFolha);
                    
        System.out.println("Idade do titular: " + idade);
        
        //Crit�rio para pagamento da Parcela de Risco: Funcion�rio deve ter 5 anos (60 meses) ou mais entre a data de admiss�o e a data de ades�o ao plano
        if ( difTempoDeAdesao >= 60)
        {
            if (idade < 25)
            {
                valorTitularParcelaRisco = PARCELA_RISCO_COTA1;
            }
            else if (idade < 30)
            {
                valorTitularParcelaRisco = PARCELA_RISCO_COTA2;
            }
            else if (idade < 40)
            {
                valorTitularParcelaRisco = PARCELA_RISCO_COTA3;
            }
            else if (idade < 50)
            {
                valorTitularParcelaRisco = PARCELA_RISCO_COTA4;
            }
            else if (idade < 60)
            {
                valorTitularParcelaRisco = PARCELA_RISCO_COTA5;
            }
            else
            {
                valorTitularParcelaRisco = PARCELA_RISCO_COTA6;
            }
        }
        else
        {
            System.out.println("Titular " + iFuncionario.getMatricula() + " possui menos de 5 anos de ades�o em rela��o � data de admiss�o.");
        }
        System.out.println("valorTitularParcelaRisco: " + valorTitularParcelaRisco);
        
        //C�lculo da Parcela de Risco dos Dependentes
        for (Dependente dependente : dependentes) 
        {
            if (dependente.isConjuge()) 
            {
                valorConjuge = descontos[1];
                conjugue = 1;
                if(dependente.getNascimento() == null)
                {
                    throw  new AplicacaoException("Data de nascimento n�o preenchida do c�njuge "+dependente.getNome()+" do titular "+dependente.getMatricula());
                }
                else
                {
                    idade = Data.anoDiffDecimal(dependente.getNascimento(), dataFolha);
                    
                    System.out.println("Idade do c�njuge: " + idade);
                    
                    //Crit�rio para pagamento da Parcela de Risco: Funcion�rio deve ter 5 anos (60 meses) ou mais entre a data de admiss�o e a data de ades�o ao plano
                    if ( difTempoDeAdesao >= 60)
                    {
                        if (idade < 25)
                        {
                            valorConjugeParcelaRisco = PARCELA_RISCO_COTA1;
                        }
                        else if (idade < 30)
                        {
                            valorConjugeParcelaRisco = PARCELA_RISCO_COTA2;
                        }
                        else if (idade < 40)
                        {
                            valorConjugeParcelaRisco = PARCELA_RISCO_COTA3;
                        }
                        else if (idade < 50)
                        {
                            valorConjugeParcelaRisco = PARCELA_RISCO_COTA4;
                        }
                        else if (idade < 60)
                        {
                            valorConjugeParcelaRisco = PARCELA_RISCO_COTA5;
                        }
                        else
                        {
                            valorConjugeParcelaRisco = PARCELA_RISCO_COTA6;
                        }
                    }
                    else
                    {
                        System.out.println("Titular " + iFuncionario.getMatricula() + " possui menos de 5 anos de ades�o em rela��o � data de admiss�o.");
                    }
                    System.out.println("valorConjugeParcelaRisco: " + valorConjugeParcelaRisco);
                }
            } 
            else 
            {
                if(dependente.getNascimento() == null)
                {
                    throw  new AplicacaoException("Data de nascimento n�o preenchida do dependente "+dependente.getNome()+" do titular "+dependente.getMatricula());
                }
                else
                {
                    idade = Data.anoDiffDecimal(dependente.getNascimento(), dataFolha);
                    
                    System.out.println("Idade do dependente: " + idade);
                    
                    //Crit�rio para pagamento da Parcela de Risco: Funcion�rio deve ter 5 anos (50 meses) ou mais entre a data de admiss�o e a data de ades�o ao plano
                    if ( difTempoDeAdesao >= 60)
                    {
                        if (dependente.isAgregado())
                        {
                            if ( (idade > 17) && (idade < 25) )
                            {
                                valorAgregadoMenorParcelaRisco += PARCELA_RISCO_COTA1;
                                System.out.println("valorAgregadoMenorParcelaRisco: " + valorAgregadoMenorParcelaRisco);
                            }
                            else
                            {
                                if (idade < 30)
                                {
                                    valorAgregadoMaiorParcelaRisco += PARCELA_RISCO_COTA2;
                                }
                                else if (idade < 40)
                                {
                                    valorAgregadoMaiorParcelaRisco += PARCELA_RISCO_COTA3;
                                }
                                else if (idade < 50)
                                {
                                    valorAgregadoMaiorParcelaRisco += PARCELA_RISCO_COTA4;
                                }
                                else if (idade < 60)
                                {
                                    valorAgregadoMaiorParcelaRisco += PARCELA_RISCO_COTA5;
                                }
                                else
                                {
                                    valorAgregadoMaiorParcelaRisco += PARCELA_RISCO_COTA6;
                                }
                                System.out.println("valorAgregadoMaiorParcelaRisco: " + valorAgregadoMaiorParcelaRisco);
                            }
                        }
                        else if (idade < 25)
                        {
                            valorDependenteParcelaRisco += PARCELA_RISCO_COTA1;
                        }
                        else if (idade < 30)
                        {
                            valorDependenteParcelaRisco += PARCELA_RISCO_COTA2;
                        }
                        else if (idade < 40)
                        {
                            valorDependenteParcelaRisco += PARCELA_RISCO_COTA3;
                        }
                        else if (idade < 50)
                        {
                            valorDependenteParcelaRisco += PARCELA_RISCO_COTA4;
                        }
                        else if (idade < 60)
                        {
                            valorDependenteParcelaRisco += PARCELA_RISCO_COTA5;
                        }
                        else
                        {
                            valorDependenteParcelaRisco += PARCELA_RISCO_COTA6;
                        }
                    }
                    else
                    {
                        System.out.println("Titular " + iFuncionario.getMatricula() + " possui menos de 5 anos de ades�o em rela��o � data de admiss�o.");
                    }
                    System.out.println("valorDependenteParcelaRisco: " + valorDependenteParcelaRisco);
                }
                idade = dependente.getNascimento() != null ? Data.anoDiffDecimal(dependente.getNascimento(), dataFolha) : 0;
                if (dependente.isAgregado() || idade > 18) 
                {
                    if (idade > 24) 
                    {
                        totalAgregadoMaior++;
                        totalAgregadoMaiorSomatorio += valorTitular; ///Nova regra, maior ou igual a 25 anos custeio integral do valor do titular
                    }
                    else
                    {
                        totalAgregadoMenor++;
                        totalAgregadoMenorSomatorio += valorDescontoAgregadoMenor25;
                    }
                }
                else
                {
                    totalDep++;
                }
            }
        }
    
        
        //FIM

        Double saldo = 0d;
        Double totalDescontos = 0d;
        for (PlanservDTO planserv : descontosPlanserv) {
            totalDescontos += planserv.getValorVerba();
            if (planserv.getVerbasPlanservEnum() == VerbasPlanservEnum.CO_PARTICIPACAO) {
                valorCoParticipacao += planserv.getValorVerba();
            } else {
                saldo += planserv.getValorVerba();

                saldo = this.calculaSaldo(saldo, valorTitular);

                saldo = this.calculaSaldo(saldo, valorConjuge);

                /*Uma lei no Planserv, garante ao Titular pagar por no m�ximo 4 dependentes, com o conjuge incluso.
                    Ent�o um titular pagar� no m�ximo por: (3 dependentes mais conjute, ou 4 dependentes)*/
                saldo = this.calculaSaldo(saldo, Math.min(totalDep,4 - conjugue) * valorDescontoDependente);

                saldo = this.calculaSaldo(saldo, totalAgregadoMenorSomatorio + (valorEspecial * totalAgregadoMenor));

                saldo = this.calculaSaldo(saldo, totalAgregadoMaiorSomatorio + (valorEspecial * totalAgregadoMaior));

                saldo = this.calculaSaldo(saldo, (totalDep + conjugue + 1) * valorEspecial);
                
                // IMPLEMENTA��O PARA INCLUIR O SALDO DA PARCELA DE RISCO AO SALDO TOTAL - IMPLEMENTADO EM 20/05/2021
                //INICIO
                
                saldo = saldo + valorTitularParcelaRisco + valorConjugeParcelaRisco + valorDependenteParcelaRisco 
                      + valorAgregadoMenorParcelaRisco + valorAgregadoMaiorParcelaRisco;
                
                //FIM

            }
        }

        if (saldo != 0d) {
            System.out.println(iFuncionario.getNome()+" "+saldo+" "+totalDescontos);
            if(!desprezarErros){
                throw new AplicacaoException("Erro no calculo do desconto do planserv.");
            }
        }
        detalheMensal.setSaldo(saldo);
        detalheMensal.setTotalDescontos(totalDescontos);


        detalheMensal.setNumeroMatriculaTopSaude(iFuncionario.getMatriculaHR() != null && iFuncionario.getMatriculaHR() > 0 ? iFuncionario.getMatriculaHR() : iFuncionario.getCodigoDominio());
        detalheMensal.setCodigoDominio(iFuncionario.getCodigoDominio());
        detalheMensal.setRelacaoDependencia(relacaoDependencia);//matricula da flem
        detalheMensal.setNomeTitular(iFuncionario.getNome());
        detalheMensal.setCpf(iFuncionario.getCpf());

        detalheMensal.setBaseCalculo(baseCalculo);//sal�rio do funcion�rio

        detalheMensal.setQtdDependente(totalDep);
        /*Uma lei no Planserv, garante ao Titular pagar por no m�ximo 4 dependentes, com o conjuge incluso.
          Ent�o um titular pagar� no m�ximo por: (3 dependentes mais conjute, ou 4 dependentes)*/
        detalheMensal.setValorDependente(Math.min(totalDep,4 - conjugue) * valorDescontoDependente);// valor total descontado para todos os dependentes

        detalheMensal.setQtdAgregadoMenor(totalAgregadoMenor);
        detalheMensal.setValorDescontoAgregadoMenor(totalAgregadoMenorSomatorio);// valor total descontado para todos os agregados
        detalheMensal.setQtdAgregadoMaior(totalAgregadoMaior);
        detalheMensal.setValorDescontoAgregadoMaior(totalAgregadoMaiorSomatorio);

        detalheMensal.setValorTitular(valorTitular);
        detalheMensal.setValorEspecial((totalDep + conjugue + 1 + totalAgregadoMenor + totalAgregadoMaior) * valorEspecial);//total de Dependentes + o titular

        //campos novos: atualiza��o feita em 20/09/2011
        //INI
        detalheMensal.setValorDependenteRetroativo(valorDependenteRetroativo);
        detalheMensal.setValorDescontoAgregadoMenorRetroativo(valorDescontoAgregadoMenorRetroativo);
        detalheMensal.setValorDescontoAgregadoMaiorRetroativo(valorDescontoAgregadoMaiorRetroativo);
        detalheMensal.setValorTitularRetroativo(valorTitularRetroativo);
        detalheMensal.setValorEspecialRetroativo(valorEspecialRetroativo);
        detalheMensal.setValorConjuge(valorConjuge);
        detalheMensal.setValorConjugeRetroativo(valorConjugeRetroativo);
        detalheMensal.setValorCoParticipacao(valorCoParticipacao);
        //FIM
        
        //IMPLEMENTA��O DOS NOVOS CAMPOS DO LAYOUT - IMPLEMENTADO EM 12/05/2021
        //IN�CIO
        detalheMensal.setValorTitularParcelaRisco(valorTitularParcelaRisco);
        detalheMensal.setValorConjugeParcelaRisco(valorConjugeParcelaRisco);
        detalheMensal.setValorDependenteParcelaRisco(valorDependenteParcelaRisco);
        detalheMensal.setValorAgregadoMenorParcelaRisco(valorAgregadoMenorParcelaRisco);
        detalheMensal.setValorAgregadoMaiorParcelaRisco(valorAgregadoMaiorParcelaRisco);
        //FIM
        return detalheMensal;
    }

    private String matriculasParaString(Collection<Integer> matriculas) throws AplicacaoException {
        if (matriculas.isEmpty()) {
            throw new AplicacaoException("N�o foi poss�vel obter os descontos do Planserv. Verifique se j� foi gerada folha!");
        }
        StringBuilder sb = new StringBuilder();
        for (Integer matricula : matriculas) {
            sb.append(matricula).append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private Double calculaSaldo(Double saldo, Double valor) {
        saldo = MoedaUtil.removePrecisao(saldo - valor, 2);
        return saldo;
    }
    
    public List<Layout> somenteDetalheMensal(List<Layout> layout){
        List<Layout> somenteDetalhe = new ArrayList<Layout>();
        for(Layout detalhe : layout){
            if(DetalheMensal.class.isInstance(detalhe)){
                somenteDetalhe.add(detalhe);
            }
        }
        return somenteDetalhe;
    }

    public List<Dependente> obterDependentes(String matricula){
        return new RHServico().obterDependentesPlanservPorMatriculas(matricula);
    }
    
    public Funcionario obterFuncionario(String matricula){
        return (Funcionario)new RHServico().obterFuncionarioPorMatricula(Integer.parseInt(matricula));
    }

    public ValidacaoFolha obterValidacaoFolha(String mes, String ano) throws AplicacaoException{
        RHServico rh = new RHServico();
        ValidacaoFolha validacaoFolha = new ValidacaoFolha();
        Double valorDescontoAgregadoMenor25 = Double.parseDouble(PropertiesUtil.getProperty("valorDescontoAgregadoMenor25"));
        Double valorEspecial = Double.parseDouble(PropertiesUtil.getProperty("valorEspecial"));

        Calendar cDataReferencia = Calendar.getInstance();
        cDataReferencia.set(Calendar.MONTH, Integer.parseInt(mes));
        cDataReferencia.set(Calendar.YEAR, Integer.parseInt(ano));
        cDataReferencia.set(Calendar.DAY_OF_MONTH, 1);
        
        validacaoFolha.setMesReferencia(Data.formataData(cDataReferencia.getTime()));

        cDataReferencia.set(Calendar.DAY_OF_MONTH, cDataReferencia.getActualMaximum(Calendar.DAY_OF_MONTH));

        Map<Integer, Collection<PlanservDTO>> mapVerbas = rh.obterValoresPlanservDescontados(mes, ano); //Vem do CHRBI, com base nos descontos em folha

        String matriculas = this.matriculasParaString(mapVerbas.keySet());
        Map<Integer,Double> salarios = rh.salariosNaData(matriculas,cDataReferencia.getTime());
        //List<IFuncionario> funcionarioList = rh.obterTodosPorPlanservMatriculasFuncionario(matriculas);//Deixado de utilizar por muita gente n�o estar sendo sinalizada no sistema que tem planserv
        List<Funcionario> funcionarioList = rh.obterFuncionarios();
        Map<Integer, List<Dependente>> depMap = rh.obterMapDependentesPlanservPorMatriculas(matriculas); // s� vem dependente ativo 
        Double totalDescontadoFLEM = 0d;

        for(IFuncionario func : funcionarioList){
            if (mapVerbas.containsKey(func.getMatricula())) {
                Double valorEspecialCondicional = 0d;
                Double totalDescontadoFuncionario = 0d;
                Double valorCoParticipacao = 0d;

                for(PlanservDTO verba : mapVerbas.get(func.getMatricula())){
                    if(verba.getVerbasPlanservEnum().equals(VerbasPlanservEnum.ESPECIAL)){
                        valorEspecialCondicional = valorEspecial;
                    }
                    if(verba.getVerbasPlanservEnum().equals(VerbasPlanservEnum.CO_PARTICIPACAO)){
                        valorCoParticipacao = verba.getValorVerba();
                    }
                    totalDescontadoFuncionario += verba.getValorVerba();
                }
                totalDescontadoFLEM += totalDescontadoFuncionario;
                //Calculo para gerar o digito verificador do Titular
                 int dv = StringUtil.modulo11(func.getCpf()+"00");
                //-------

                GrupoFamiliar grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setNumAssociadoResponsavel(StringUtil.formatarCampoNumerico(func.getCpf(), "00000000000")+"00"+Integer.toString(dv));
                grupoFamiliar.setCpf(StringUtil.formatarCampoNumerico(func.getCpf(), "00000000000"));
                grupoFamiliar.setBaseCalculo(salarios.get(func.getMatricula()));

                Double[] linhaDescontos = obterDescontoPlanserv(grupoFamiliar.getBaseCalculo()); //pega o desconto do chrbi com base no sal�rio(baseCalculo)

                Associado associado = new Associado();
                associado.setNumAssociado(grupoFamiliar.getNumAssociadoResponsavel());
                associado.setGrauDependencia("0");
                associado.setPagamentos(new ArrayList<Pagamento>());

                associado.getPagamentos().add(new Pagamento(TipoPagamentoPlanserv.PRINCIPAL, linhaDescontos[0] + valorEspecialCondicional));//Se especial adiciona 65 ao desconto

                if(valorCoParticipacao > 0){
                    associado.getPagamentos().add(new Pagamento(TipoPagamentoPlanserv.COPARTICIPACAO, valorCoParticipacao));
                }
                grupoFamiliar.getAssociados().add(associado);
                if(depMap.get(func.getMatricula()) != null){
                    for(Dependente dependente : depMap.get(func.getMatricula())){
                        associado = new Associado();
                        //Calculo para gerar o digito verificador do Dependente
                        int dv1 = StringUtil.modulo11(func.getCpf()+"0"+dependente.getSequencia());
                        //---------
                        associado.setNumAssociado(StringUtil.formatarCampoNumerico(func.getCpf(), "00000000000")+StringUtil.formatarCampoNumerico(dependente.getSequencia().toString(), "00")+Integer.toString(dv1));
                        associado.setGrauDependencia(dependente.getParentesco().getCodigoPlanserv());
                        associado.setPagamentos(new ArrayList<Pagamento>());

                        if (dependente.isConjuge()) {
                            associado.getPagamentos().add(new Pagamento(TipoPagamentoPlanserv.PRINCIPAL, linhaDescontos[1] + valorEspecialCondicional));
                        } else {
                            float idade = dependente.getNascimento() != null ? Data.anoDiffDecimal(dependente.getNascimento(), cDataReferencia.getTime()) : 0;
                            if (dependente.isAgregado() || idade > 18) {
                                if (idade > 24) {//Se agregado ou dependente maior de 18 paga-se valor integral do Titular
                                    associado.getPagamentos().add(new Pagamento(TipoPagamentoPlanserv.PRINCIPAL, linhaDescontos[0] + valorEspecialCondicional));
                                } else{
                                    associado.getPagamentos().add(new Pagamento(TipoPagamentoPlanserv.PRINCIPAL, valorDescontoAgregadoMenor25 + valorEspecialCondicional));
                                }
                            }else{
                                associado.getPagamentos().add(new Pagamento(TipoPagamentoPlanserv.PRINCIPAL, linhaDescontos[2] + valorEspecialCondicional));
                            }
                        }
                        grupoFamiliar.getAssociados().add(associado);
                    }
                }
                validacaoFolha.getGruposFamiliares().add(grupoFamiliar);
            }
        }
        validacaoFolha.setValorTotalOrgao(totalDescontadoFLEM);

        return validacaoFolha;
    }
}
