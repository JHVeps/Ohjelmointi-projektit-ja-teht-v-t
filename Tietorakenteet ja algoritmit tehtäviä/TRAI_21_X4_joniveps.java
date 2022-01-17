import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public class TRAI_21_X4_joniveps implements TRAI_21_X4 {
/**
     * AIKAVAATIVUUS
     * Molempien algoritmien aikavaativuus on O(puun_korkeus). Puun_korkeus = O(logn), joten Aikavaativuus on O(logn).
     * Puusta hakeminen/läpikäynti O(logn). Muiden operaatioiden aikavaativuus on O(1) vakioaikaista. 
     */
    /**
     * Palauttaa binääripuun sisäjärjestyksessä viimeisen solmun.
     *
     * @param T Tarkasteltava puu.
     * @return Viimeinen solmu tai null jos puu T on tyhjä. 
     */
    @Override
    public BTreeNode sisaViimeinen(BTree T) {
        BTreeNode lastNode = T.getRoot();

        if (lastNode == null) {
            return null;
        }
        while (lastNode.getRightChild() != null) {  //O(logn)
            lastNode = lastNode.getRightChild();
        }
        return lastNode;
        
    }

    /**
     * Palauttaa binääripuun solmun n edeltäjän sisäjärjestyksessä.
     *
     * @param n Binääripuun solmu.
     * @return edeltäjäsolmu tai null jollei edeltäjää ole.
     */
    @Override
    public BTreeNode sisaEdellinen(BTreeNode n) {

        if (n.getLeftChild() != null) {
            BTreeNode prevNode = n.getLeftChild();
            
            while (prevNode.getRightChild() != null) {  //O(logn)
                prevNode = prevNode.getRightChild();
            }
            return prevNode;
        } else {
            BTreeNode node = n;
            BTreeNode parent = n.getParent();
            
            while (parent != null) {                //O(logn)
                if (parent.getRightChild() == node) {
                    return parent;
                } else {
                    node = parent;
                    parent = parent.getParent();

                }
            }

        }
        return null;
    }
}
