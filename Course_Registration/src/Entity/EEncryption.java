package Entity;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EEncryption {

	public EEncryption() {
	}
    public final String algorithm = "AES/CBC/PKCS5Padding";
    /*����� ��ȣȭ �˰���*/
    //AES�� 128��Ʈ�� ��ϴ����� ��ȣȭ�ϴµ� 128��Ʈ���� ���� ����� ���� ��� ������ �κ��� Ư�� ������ ä������ 
    //�̷��� �۾��� �е��̶�� �θ���, ��ǥ������ PKCS5, PCKS7 ����� ����
    //*��ȣȭ�� ���� ������ Block Cipher Mode -> ��ǥ������ CBC, ECB
    //CBC ���� - CBC�� ����� �״�� ��ȣȭ �����ʰ� ������ ��ȣȭ�ߴ� ��ϰ� XOR ������ �� ������ ��ȣȭ�ϱ⿡ ���� ������ ���� �ؽ�Ʈ���� ���� �ٸ� ��ȣ���� ���Ե�*/
    private final String key = "01234567890123456789012345678901";
    /*��ȣȭ, ��ȣȭ�� ����� key*/
    private final String iv = key.substring(0, 16); // 16byte
    //ù��° ����� ���� ��ȣȭ ����� ���� ������ �̸� ���� IV(initialization vector)�� �̿���.
    //AES�� 128��Ʈ(16����Ʈ)������ ��ȣȭ �ϱ⶧���� IV���� 16����Ʈ ũ�⿩����. IV�� �����Ǹ� �� ���� ������ ù��° ����� ��ȣȭ

    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
}
