package com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants;

public class TenantContext {
    
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    public static void setTenant(String tenantDb) {
        
        CURRENT_TENANT.set(tenantDb); 
    }
    public static String getTenant(){ 

        return CURRENT_TENANT.get(); 
    }
    public static void clear(){ 
        
        CURRENT_TENANT.remove();
    }
}
