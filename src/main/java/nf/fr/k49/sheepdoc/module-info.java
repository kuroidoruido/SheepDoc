
module sheepdoc.app {
    exports nf.fr.k49.sheepdoc;
    requires java.base;
    requires java.desktop;
    // automatic modules -- named in manifest
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
}