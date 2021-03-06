package self.aub.product.code.generator.generator.pkg.act;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import self.aub.product.code.generator.bean.Layer;
import self.aub.product.code.generator.bean.Table;
import self.aub.product.code.generator.config.GeneratorConfig;
import self.aub.product.code.generator.generator.Generator;
import self.aub.product.code.generator.util.ConvertUtil;

/**
 * @author aub
 */
public class MysqlActGenerator extends Generator {
    private static final Logger LOG = LoggerFactory.getLogger(MysqlActGenerator.class);

    public static void generate(Table table, String basePackage, String systemPackage, String outputDir) {
        Layer layer = GeneratorConfig.getLayer();
        String tableNameClass = table.getTableNameClass();
        LOG.debug("generate act : {}{}", tableNameClass, layer.getFileSuffixAct());

        VelocityContext context = new VelocityContext();
        context.put("basePackage", basePackage);
        context.put("systemPackage", systemPackage);
        context.put("tableNameClass", tableNameClass);
        context.put("tableRemarks", table.getTableRemarks());
        context.put("tableNameVariable", table.getTableNameVariable());
        context.put("requestMapping", ConvertUtil.conver2RequestMapping(table.getTableNameDb()));
        context.put("primaryKey", table.getPrimaryKey());
        context.put("layer", layer);

        String outputFilePath = generateOutputFilePath(tableNameClass, outputDir, layer.getLayerNameAct(), layer.getFileSuffixAct());
        write2FileBySchema("/act/MysqlAct.vm", context, outputFilePath);

    }
}
