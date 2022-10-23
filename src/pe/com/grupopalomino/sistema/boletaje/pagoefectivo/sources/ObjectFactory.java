
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EncryptTextResponse }
     * 
     */
    public EncryptTextResponse createEncryptTextResponse() {
        return new EncryptTextResponse();
    }

    /**
     * Create an instance of {@link Signer }
     * 
     */
    public Signer createSigner() {
        return new Signer();
    }

    /**
     * Create an instance of {@link SignerResponse }
     * 
     */
    public SignerResponse createSignerResponse() {
        return new SignerResponse();
    }

    /**
     * Create an instance of {@link EncryptText }
     * 
     */
    public EncryptText createEncryptText() {
        return new EncryptText();
    }

    /**
     * Create an instance of {@link DecryptText }
     * 
     */
    public DecryptText createDecryptText() {
        return new DecryptText();
    }

    /**
     * Create an instance of {@link DecryptTextResponse }
     * 
     */
    public DecryptTextResponse createDecryptTextResponse() {
        return new DecryptTextResponse();
    }

}
