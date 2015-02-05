package ca.mcgill.mcb.pcingola.snpSift.testCases;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import ca.mcgill.mcb.pcingola.snpSift.SnpSiftCmdExtractFields;
import ca.mcgill.mcb.pcingola.util.Gpr;
import ca.mcgill.mcb.pcingola.vcf.EffFormatVersion;
import ca.mcgill.mcb.pcingola.vcf.VcfEffect;
import ca.mcgill.mcb.pcingola.vcf.VcfHeader;
import ca.mcgill.mcb.pcingola.vcf.VcfHeaderInfo;

/**
 * Extract fields test cases
 *
 * @author pcingola
 */
public class TestCasesExtractFields extends TestCase {

	public static boolean debug = false;

	/**
	 * Extract fields and return the output lines
	 */
	List<String> extract(String vcfFileName, String fieldExpression) {
		String args[] = { vcfFileName, fieldExpression };
		SnpSiftCmdExtractFields ssef = new SnpSiftCmdExtractFields(args);

		List<String> linesList = ssef.run(true);

		if (debug) {
			for (String line : linesList)
				Gpr.debug(line);
		}

		return linesList;
	}

	/**
	 * Extract fields form a file and check that the line matches (only one line expected from the file)
	 */
	void extractAndCheck(String vcfFileName, String fieldExpression, String expected) {
		List<String> linesList = extract(vcfFileName, fieldExpression);
		if (linesList.size() != 1) throw new RuntimeException("Only one line expected");
		Assert.assertEquals(expected, linesList.get(0));
	}

	/**
	 * Check headers vs map2num
	 */
	public void test_00() {
		Gpr.debug("Test");
		VcfHeader vcfHeader = new VcfHeader();

		// Make sure all map2num are in the INFO field
		if (debug) System.out.println("ANN:");
		for (String annField : VcfEffect.mapAnn2Num(EffFormatVersion.FORMAT_ANN_1).keySet()) {
			VcfHeaderInfo vi = vcfHeader.getVcfInfo(annField);
			if (debug) System.out.println("\t" + annField + "\t" + vi);
			Assert.assertTrue("Cannot find INFO header for field '" + annField + "'", vi != null);
		}

		// Make sure all map2num are in the INFO field
		if (debug) System.out.println("EFF:");
		for (String effField : VcfEffect.mapAnn2Num(EffFormatVersion.FORMAT_EFF_4).keySet()) {
			VcfHeaderInfo vi = vcfHeader.getVcfInfo(effField);
			if (debug) System.out.println("\t" + effField + "\t" + vi);
			Assert.assertTrue("Cannot find INFO header for field '" + effField + "'", vi != null);
		}
	}

	/**
	 * Extract fields
	 */
	public void test_01() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "CHROM", "1");
	}

	public void test_02() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "POS", "902133");
	}

	public void test_03() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "ID", "id_1_902133");
	}

	public void test_04() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "REF", "C");
	}

	public void test_05() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "ALT", "T");
	}

	public void test_06() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "FILTER", "PASS");
	}

	public void test_07() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "AF", "0.001");
	}

	public void test_08() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].EFFECT", "STOP_GAINED");
	}

	public void test_09() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].IMPACT", "HIGH");
	}

	public void test_10() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].FUNCLASS", "NONSENSE");
	}

	public void test_11() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].CODON", "Cga/Tga");
	}

	public void test_12() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].AA", "R45*");
	}

	public void test_13() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].AA_LEN", "611");
	}

	public void test_14() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].GENE", "PLEKHN1");
	}

	public void test_15() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].BIOTYPE", "protein_coding");
	}

	public void test_16() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].CODING", "CODING");
	}

	public void test_17() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].TRID", "ENST00000379410");
	}

	public void test_18() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "EFF[*].EXID", "1");
	}

	public void test_19() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "LOF[*].GENE", "PLEKHN1");
	}

	public void test_20() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "LOF[*].GENEID", "ENSG00000187583");
	}

	public void test_23() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "NMD[*].GENE", "PLEKHN1");
	}

	public void test_24() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "NMD[*].GENEID", "ENSG00000187583");
	}

	public void test_25() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "NMD[*].NUMTR", "5");
	}

	public void test_26() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_01.eff.vcf", "NMD[*].PERC", "0.6");
	}

	public void test_27() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_27.vcf", "GEN[0].AD", "16,2");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_28() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_28.vcf", "GEN[HG00097].GT", "1|0");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_29() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_28.vcf", "GEN[HG00102].AP", "0.005,0.095");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_30() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_28.vcf", "GEN[HG00101].AP[1]", "0.123");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_31() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_31.vcf", "EFF[*].AA", "c.*568C>A");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_31_ann() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_31.ann.vcf", "ANN[*].CODON", "c.*568C>A");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_32() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].AA", "p.Glu15Gly");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].HGVS", "p.Glu15Gly");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].HGVS_P", "p.Glu15Gly");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].HGVS_PROT", "p.Glu15Gly");
	}

	/**
	 * Extract fields using sample names
	 */
	public void test_33() {
		Gpr.debug("Test");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].CODON", "c.44A>G");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].HGVS_DNA", "c.44A>G");
		extractAndCheck("test/extractFields_32.vcf", "ANN[*].HGVS_C", "c.44A>G");
	}

}
