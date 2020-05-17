package it.unibo.oop.mge.c3d;

/**
 * 
 * An object that can apply perspective to a Mesh.
 *
 */
public interface MeshDrawer {
    /**
     * 
     * @return the Mesh2D obtained from applying perspective to this Drawer's Mesh
     */
    Mesh2D render();

}
