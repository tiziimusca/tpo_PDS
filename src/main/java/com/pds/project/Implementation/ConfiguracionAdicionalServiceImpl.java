package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.ConfiguracionAdicional;
import com.pds.project.Repository.IConfiguracionAdicionalRepository;
import com.pds.project.ServiceInterface.IConfiguracionAdicionalService;

@Service
public class ConfiguracionAdicionalServiceImpl implements IConfiguracionAdicionalService {

    @Autowired
    private IConfiguracionAdicionalRepository repoConfiguracionAdicional;

    @Override
    public List<ConfiguracionAdicional> getConfiguracionesAdicional() {
        return repoConfiguracionAdicional.findAll();
    }

    public enum ResultadoConfiguracionAdicional {
        OK,
        CONFIG_DUPLICADA,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoConfiguracionAdicional guardarConfiguracionAdicional(ConfiguracionAdicional config) {

        if (repoConfiguracionAdicional.existsByPedidoId(config.getPedidoId())) {
            return ResultadoConfiguracionAdicional.CONFIG_DUPLICADA;
        }

        try {
            repoConfiguracionAdicional.save(config);
            return ResultadoConfiguracionAdicional.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoConfiguracionAdicional.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public ConfiguracionAdicional getConfiguracionAdicionalById(Long id) {
        return repoConfiguracionAdicional.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarConfiguracionAdicional(Long id) {
        if (repoConfiguracionAdicional.existsById(id)) {
            repoConfiguracionAdicional.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResultadoConfiguracionAdicional actualizarConfiguracionAdicional(long id,
            ConfiguracionAdicional nuevosDatos) {
        ConfiguracionAdicional configuracionAdicionalExistente = repoConfiguracionAdicional.findById(id).orElse(null);

        try {
            configuracionAdicionalExistente.setConfiguracionAdicional(nuevosDatos.getConfiguracionAdicional());
            configuracionAdicionalExistente.setPedidoId(nuevosDatos.getPedidoId());
            repoConfiguracionAdicional.save(configuracionAdicionalExistente);
            return ResultadoConfiguracionAdicional.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoConfiguracionAdicional.ERROR_DESCONOCIDO;
        }

    }
}
