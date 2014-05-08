package org.dynjs.runtime.linker.java.instance;

import org.dynjs.runtime.linker.java.ReferenceValueFilter;
import org.dynjs.runtime.linker.java.NullReplacingFilter;
import org.projectodd.rephract.builder.LinkBuilder;
import org.projectodd.rephract.java.instance.InstancePropertySetLink;
import org.projectodd.rephract.java.reflect.ResolverManager;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author Bob McWhirter
 */
public class JSJavaInstancePropertySetLink extends InstancePropertySetLink {

    public JSJavaInstancePropertySetLink(LinkBuilder builder, ResolverManager resolverManager) throws Exception {
        super( builder, resolverManager );
    }

    @Override
    public boolean guard(Object receiver, String propertyName, Object value) {
        System.err.println( "receiver: " +receiver );
        System.err.println( "filtered: " + ReferenceValueFilter.INSTANCE.filter( receiver ) );
        return super.guard(ReferenceValueFilter.INSTANCE.filter(receiver), propertyName, value);
    }

    @Override
    public MethodHandle target() throws Exception {
        this.builder = this.builder.filter( 0, ReferenceValueFilter.INSTANCE );
        return super.target();
    }
}
