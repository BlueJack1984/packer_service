package nu.xom;

import org.apache.xerces.parsers.DTDConfiguration;
import org.apache.xerces.parsers.SAXParser;

class XML1_0Parser extends SAXParser {
    XML1_0Parser() {
        super(new DTDConfiguration());
    }
}
