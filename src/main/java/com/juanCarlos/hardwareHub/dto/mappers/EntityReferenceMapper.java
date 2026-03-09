package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import com.juanCarlos.hardwareHub.entity.CajaEntity;
import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import com.juanCarlos.hardwareHub.entity.CpuEntity;
import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import com.juanCarlos.hardwareHub.entity.GpuEntity;
import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import com.juanCarlos.hardwareHub.entity.PsuEntity;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import com.juanCarlos.hardwareHub.entity.PublicacionMontajeEntity;
import com.juanCarlos.hardwareHub.entity.RamEntity;
import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;

public final class EntityReferenceMapper {

    private EntityReferenceMapper() {
    }

    public static FabricanteEntity toFabricanteEntity(Long id) {
        if (id == null) {
            return null;
        }
        FabricanteEntity entity = new FabricanteEntity();
        entity.setId(id);
        return entity;
    }

    public static RamEntity toRamEntity(Long id) {
        if (id == null) {
            return null;
        }
        RamEntity entity = new RamEntity();
        entity.setId(id);
        return entity;
    }

    public static CpuEntity toCpuEntity(Long id) {
        if (id == null) {
            return null;
        }
        CpuEntity entity = new CpuEntity();
        entity.setId(id);
        return entity;
    }

    public static GpuEntity toGpuEntity(Long id) {
        if (id == null) {
            return null;
        }
        GpuEntity entity = new GpuEntity();
        entity.setId(id);
        return entity;
    }

    public static RefrigeracionEntity toRefrigeracionEntity(Long id) {
        if (id == null) {
            return null;
        }
        RefrigeracionEntity entity = new RefrigeracionEntity();
        entity.setId(id);
        return entity;
    }

    public static CajaEntity toCajaEntity(Long id) {
        if (id == null) {
            return null;
        }
        CajaEntity entity = new CajaEntity();
        entity.setId(id);
        return entity;
    }

    public static PlacaBaseEntity toPlacaBaseEntity(Long id) {
        if (id == null) {
            return null;
        }
        PlacaBaseEntity entity = new PlacaBaseEntity();
        entity.setId(id);
        return entity;
    }

    public static PsuEntity toPsuEntity(Long id) {
        if (id == null) {
            return null;
        }
        PsuEntity entity = new PsuEntity();
        entity.setId(id);
        return entity;
    }

    public static AlmacenamientoEntity toAlmacenamientoEntity(Long id) {
        if (id == null) {
            return null;
        }
        AlmacenamientoEntity entity = new AlmacenamientoEntity();
        entity.setId(id);
        return entity;
    }

    public static UsuarioEntity toUsuarioEntity(Long id) {
        if (id == null) {
            return null;
        }
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(id);
        return entity;
    }

    public static MontajeEntity toMontajeEntity(Long id) {
        if (id == null) {
            return null;
        }
        MontajeEntity entity = new MontajeEntity();
        entity.setId(id);
        return entity;
    }

    public static ComentarioEntity toComentarioEntity(Long id) {
        if (id == null) {
            return null;
        }
        ComentarioEntity entity = new ComentarioEntity();
        entity.setId(id);
        return entity;
    }

    public static PublicacionEntity toPublicacionEntity(Long id) {
        if (id == null) {
            return null;
        }
        PublicacionEntity entity = new PublicacionEntity();
        entity.setId(id);
        return entity;
    }

    public static PublicacionMontajeEntity toPublicacionMontajeEntity(Long id) {
        if (id == null) {
            return null;
        }
        PublicacionMontajeEntity entity = new PublicacionMontajeEntity();
        entity.setId(id);
        return entity;
    }
}
