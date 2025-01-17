
package warehouse.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getPriceReturn" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getPriceReturn"
})
@XmlRootElement(name = "getPriceResponse")
public class GetPriceResponse {

    protected double getPriceReturn;

    /**
     * 获取getPriceReturn属性的值。
     * 
     */
    public double getGetPriceReturn() {
        return getPriceReturn;
    }

    /**
     * 设置getPriceReturn属性的值。
     * 
     */
    public void setGetPriceReturn(double value) {
        this.getPriceReturn = value;
    }

}
