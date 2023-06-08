package Entity;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EEncryption {

	public EEncryption() {
	}
    public final String algorithm = "AES/CBC/PKCS5Padding";
    /*사용할 암호화 알고리즘*/
    //AES는 128비트의 블록단위로 암호화하는데 128비트보다 작은 블록이 생길 경우 부족한 부분을 특정 값으로 채워야함 
    //이러한 작업을 패딩이라고 부르며, 대표적으로 PKCS5, PCKS7 방식이 있음
    //*암호화시 선택 가능한 Block Cipher Mode -> 대표적으로 CBC, ECB
    //CBC 권장 - CBC는 블록을 그대로 암호화 하지않고 이전에 암호화했던 블록과 XOR 연산을 한 다음에 암호화하기에 같은 내용을 갖는 텍스트여도 각각 다른 암호문을 갖게됨*/
    private final String key = "01234567890123456789012345678901";
    /*암호화, 복호화시 사용할 key*/
    private final String iv = key.substring(0, 16); // 16byte
    //첫번째 블록은 이전 암호화 블록이 없기 때문에 이를 위해 IV(initialization vector)를 이용합.
    //AES는 128비트(16바이트)단위로 암호화 하기때문에 IV또한 16바이트 크기여야함. IV가 생성되면 이 값을 가지고 첫번째 블록을 암호화

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
